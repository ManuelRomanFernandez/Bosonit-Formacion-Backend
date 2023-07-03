package com.formacion.bosonit.block7crudvalidation.teacher.application;

import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.mapper.TeacherMapper;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.teacher.respository.TeacherRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {

    TeacherMapper mapper = Mappers.getMapper(TeacherMapper.class);
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public TeacherOutputDto getFullTeacherById(String id_teacher) {
        return mapper.teacherToTeacherOutputDto(
                teacherRepository.findAll()
                        .stream()
                        .filter(e -> e.getId_teacher().equals(id_teacher))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado")));
    }

    @Override
    public TeacherSimpleOutputDto getSimpleTeacherById(String id_teacher) {
        return mapper.teacherToTeacherSimpleOutputDto(
                teacherRepository.findAll()
                        .stream()
                        .filter(e -> e.getId_teacher().equals(id_teacher))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado")));
    }

    @Override
    public Iterable<TeacherOutputDto> getAllFullTeachers(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return teacherRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(teacher -> mapper.teacherToTeacherOutputDto(teacher))
                .toList();
    }

    @Override
    public Iterable<TeacherSimpleOutputDto> getAllSimpleTeachers(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return teacherRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(teacher -> mapper.teacherToTeacherSimpleOutputDto(teacher))
                .toList();
    }

    @Override
    public TeacherOutputDto addTeachers(TeacherInputDto teacherInputDto) {
        Persona persona = personaRepository.findById(teacherInputDto.getId_persona()).orElseThrow();
        Teacher teacher = mapper.teacherInputDtoToTeacher(teacherInputDto);

        persona.setTeacher(teacher);
        teacher.setPersona(persona);

        return mapper.teacherToTeacherOutputDto(teacherRepository.save(teacher));
    }

    @Override
    public TeacherSimpleOutputDto updateTeacherById(TeacherInputDto teacherInputDto) {
        Teacher currentTeacher = teacherRepository.findAll()
                .stream()
                .filter(e -> e.getId_teacher().equals(teacherInputDto.getId_teacher()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado"));

        if(teacherInputDto.getComments() != null)
            currentTeacher.setComments(teacherInputDto.getComments());

        currentTeacher.setBranch(teacherInputDto.getBranch());

        if(teacherInputDto.getId_persona() != null){
            Persona updatedPersona = personaRepository
                    .findById(teacherInputDto.getId_persona())
                    .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

            if(!Objects.equals(currentTeacher.getPersona().getId_persona(), teacherInputDto.getId_persona())){
                Persona oldPersona = personaRepository
                        .findById(currentTeacher.getPersona().getId_persona())
                        .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));


                oldPersona.setTeacher(null);
            }

            updatedPersona.setTeacher(currentTeacher);
            currentTeacher.setPersona(updatedPersona);
        }

        return mapper.teacherToTeacherSimpleOutputDto(teacherRepository.save(currentTeacher));
    }

    @Override
    public void deleteTeacherById(String id_teacher) {
        Teacher deleteTeacher = teacherRepository.findAll()
                .stream()
                .filter(e -> e.getId_teacher().equals(id_teacher))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado"));

        personaRepository.findById(deleteTeacher.getPersona().getId_persona())
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"))
                .setTeacher(null);

        studentRepository.findAll()
                .forEach(student -> {
                    if(student.getTeacher() != null && student.getTeacher().getId_teacher().equals(id_teacher)) {
                        student.setTeacher(null);
                    }
                });

        teacherRepository.delete(deleteTeacher);
    }
}
