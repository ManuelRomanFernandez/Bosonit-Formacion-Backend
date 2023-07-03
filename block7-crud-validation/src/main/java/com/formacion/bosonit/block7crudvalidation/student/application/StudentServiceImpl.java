package com.formacion.bosonit.block7crudvalidation.student.application;

import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.controller.mapper.StudentMapper;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.teacher.respository.TeacherRepository;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService{
    StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public StudentOutputDto getFullStudentById(String id_student) {
        return mapper.studentToStudentOutputDto(
                studentRepository.findAll()
                        .stream()
                        .filter(e -> e.getId_student().equals(id_student))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado")));
    }

    @Override
    public StudentSimpleOutputDto getSimpleStudentById(String id_student) {
        return mapper.studentToStudentSimpleOutputDto(
                studentRepository.findAll()
                        .stream()
                        .filter(e -> e.getId_student().equals(id_student))
                        .findFirst().orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado")));
    }

    @Override
    public Iterable<StudentOutputDto> getAllFullStudents(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(student -> mapper.studentToStudentOutputDto(student))
                .toList();
    }

    @Override
    public Iterable<StudentSimpleOutputDto> getAllSimpleStudents(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(student -> mapper.studentToStudentSimpleOutputDto(student))
                .toList();
    }

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) {
        Persona persona = personaRepository.findById(studentInputDto.getId_persona())
                .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado"));
        Student student = mapper.studentInputDtoToTeacher(studentInputDto);

        persona.setStudent(student);
        student.setPersona(persona);

        if(studentInputDto.getId_teacher() != null){
            Teacher teacher = teacherRepository
                    .findById(studentInputDto.getId_teacher())
                    .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado"));

            teacher.getStudents().add(student);
            student.setTeacher(teacher);
        }

        return mapper.studentToStudentOutputDto(studentRepository.save(student));
    }

    @Override
    public StudentSimpleOutputDto updateStudentById(StudentInputDto studentInputDto) {
        Student currentStudent = studentRepository.findAll()
                .stream()
                .filter(e -> e.getId_student().equals(studentInputDto.getId_student()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado"));


        currentStudent.setNum_hours_week(studentInputDto.getNum_hours_week());

        if(studentInputDto.getComments() != null){
            currentStudent.setComments(studentInputDto.getComments());
        }

        currentStudent.setBranch(studentInputDto.getBranch());

        if(studentInputDto.getId_persona() != null && !Objects.equals(currentStudent.getPersona().getId_persona(), studentInputDto.getId_persona())){
            Persona oldPersona = personaRepository
                    .findById(currentStudent.getPersona().getId_persona())
                    .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));
            Persona updatedPersona = personaRepository
                    .findById(studentInputDto.getId_persona())
                    .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

            oldPersona.setStudent(null);
            updatedPersona.setStudent(currentStudent);
            currentStudent.setPersona(updatedPersona);
        }

        if(studentInputDto.getId_teacher() != null){
            Teacher updatedTeacher = teacherRepository
                    .findById(studentInputDto.getId_teacher())
                    .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado"));

            if(currentStudent.getTeacher() != null && !Objects.equals(currentStudent.getTeacher().getId_teacher(), studentInputDto.getId_teacher())){
                Teacher oldTeacher = teacherRepository
                        .findById(currentStudent.getTeacher().getId_teacher())
                        .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado"));

                oldTeacher.getStudents().remove(currentStudent);
            }

            updatedTeacher.getStudents().add(currentStudent);
            currentStudent.setTeacher(updatedTeacher);
        }

        return mapper.studentToStudentSimpleOutputDto(studentRepository.save(currentStudent));
    }

    @Override
    public void deleteStudentById(String id_student) {
        Student deletedStudent = studentRepository.findAll()
                .stream()
                .filter(e -> e.getId_student().equals(id_student))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado"));

        Integer id_persona = deletedStudent.getPersona().getId_persona();

        personaRepository.findById(id_persona)
                .orElseThrow()
                .setStudent(null);

        if (deletedStudent.getTeacher() != null)
            teacherRepository.findById(deletedStudent.getTeacher().getId_teacher())
                    .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado"))
                    .getStudents()
                    .remove(deletedStudent);

        studentRepository.delete(deletedStudent);
    }
}
