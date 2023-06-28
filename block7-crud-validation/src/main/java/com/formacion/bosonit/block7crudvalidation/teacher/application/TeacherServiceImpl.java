package com.formacion.bosonit.block7crudvalidation.teacher.application;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.teacher.respository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public TeacherOutputDto getFullTeacherById(String id_teacher) {
        return teacherRepository.findAll()
                .stream()
                .filter(e -> e.getId_teacher().equals(id_teacher))
                .findFirst().orElseThrow().teacherToTeacherOutputDto();
    }

    @Override
    public TeacherSimpleOutputDto getSimpleTeacherById(String id_teacher) {
        return teacherRepository.findAll()
                .stream()
                .filter(e -> e.getId_teacher().equals(id_teacher))
                .findFirst().orElseThrow().teacherToTeacherSimpleOutputDto();
    }

    @Override
    public Iterable<TeacherOutputDto> getAllFullTeachers(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return teacherRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(Teacher::teacherToTeacherOutputDto)
                .toList();
    }

    @Override
    public Iterable<TeacherSimpleOutputDto> getAllSimpleTeachers(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return teacherRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(Teacher::teacherToTeacherSimpleOutputDto)
                .toList();
    }

    @Override
    public TeacherOutputDto addTeachers(TeacherInputDto teacherInputDto) {
        Persona persona = personaRepository.findById(teacherInputDto.getId_persona()).orElseThrow();
        Teacher teacher = new Teacher(teacherInputDto);

        persona.setTeacher(teacher);
        teacher.setPersona(persona);

        return teacherRepository.save(teacher).teacherToTeacherOutputDto();
    }

    @Override
    public TeacherSimpleOutputDto updateTeacherById(TeacherInputDto teacherInputDto) {
        Teacher currentTeacher = teacherRepository.findAll()
                .stream()
                .filter(e -> e.getId_teacher().equals(teacherInputDto.getId_teacher()))
                .findFirst().orElseThrow();

        if(teacherInputDto.getComments() != null)
            currentTeacher.setComments(teacherInputDto.getComments());

        currentTeacher.setBranch(teacherInputDto.getBranch());

        if(teacherInputDto.getId_persona() != null){
            Persona updatedPersona = personaRepository
                    .findById(teacherInputDto.getId_persona())
                    .orElseThrow();

            if(!Objects.equals(currentTeacher.getPersona().getId_persona(), teacherInputDto.getId_persona())){
                Persona oldPersona = personaRepository
                        .findById(currentTeacher.getPersona().getId_persona())
                        .orElseThrow();


                oldPersona.setTeacher(null);
            }

            updatedPersona.setTeacher(currentTeacher);
            currentTeacher.setPersona(updatedPersona);
        }

        return teacherRepository.save(currentTeacher)
                .teacherToTeacherSimpleOutputDto();
    }

    @Override
    public void deleteTeacherById(String id_teacher) {
        Teacher deleteTeacher = teacherRepository.findAll()
                .stream()
                .filter(e -> e.getId_teacher().equals(id_teacher))
                .findFirst()
                .orElseThrow();

        personaRepository.findById(deleteTeacher.getPersona().getId_persona())
                .orElseThrow()
                .setTeacher(null);

        studentRepository.findAll()
                .stream()
                .map(e -> {
                    if(e.getTeacher() != null && e.getTeacher().getId_teacher().equals(id_teacher))
                        e.setTeacher(null);

                    return e;
                });

        teacherRepository.delete(deleteTeacher);
    }
}
