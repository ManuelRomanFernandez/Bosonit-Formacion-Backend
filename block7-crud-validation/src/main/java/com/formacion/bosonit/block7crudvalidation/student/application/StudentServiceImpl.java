package com.formacion.bosonit.block7crudvalidation.student.application;

import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.mapper.StudentMapper;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.teacher.repository.TeacherRepository;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private static final String ID_PERSON_ERROR = "No existe la persona con el id indicado";
    private static final String ID_STUDENT_ERROR = "No existe el estudiante con el id indicado";
    private static final String ID_TEACHER_ERROR = "No existe el profesor con el id indicado";

    @Override
    public StudentFullOutputDto getFullStudentById(String id_student) {
        return mapper.studentToStudentOutputDto(
                studentRepository.findById(id_student)
                        .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR)));
    }

    @Override
    public StudentSimpleOutputDto getSimpleStudentById(String id_student) {
        return mapper.studentToStudentSimpleOutputDto(
                studentRepository.findById(id_student)
                        .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR)));
    }

    @Override
    public List<StudentFullOutputDto> getAllFullStudents(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(student -> mapper.studentToStudentOutputDto(student))
                .toList();
    }

    @Override
    public List<StudentSimpleOutputDto> getAllSimpleStudents(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(student -> mapper.studentToStudentSimpleOutputDto(student))
                .toList();
    }

    @Override
    public StudentFullOutputDto addStudent(StudentInputDto studentInputDto) {
        Persona persona = personaRepository.findById(studentInputDto.getId_persona())
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR));

        Student student = mapper.studentInputDtoToStudent(studentInputDto);

        persona.setStudent(student);
        student.setPersona(persona);

        if(studentInputDto.getId_teacher() != null){
            Teacher teacher = teacherRepository
                    .findById(studentInputDto.getId_teacher())
                    .orElseThrow(() -> new EntityNotFoundException(ID_TEACHER_ERROR));

            teacher.getStudents().add(student);
            student.setTeacher(teacher);
        }

        return mapper.studentToStudentOutputDto(studentRepository.save(student));
    }

    @Override
    public StudentSimpleOutputDto updateStudentById(StudentInputDto studentInputDto) {
        Student currentStudent = studentRepository.findById(studentInputDto.getId_student())
                .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR));
        currentStudent.setNum_hours_week(studentInputDto.getNum_hours_week());

        currentStudent.setComments(studentInputDto.getComments());

        currentStudent.setBranch(studentInputDto.getBranch());

        if(studentInputDto.getId_persona() != null && !Objects.equals(currentStudent.getPersona().getId_persona(), studentInputDto.getId_persona())){
            Persona oldPersona = personaRepository
                    .findById(currentStudent.getPersona().getId_persona())
                    .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR));
            Persona updatedPersona = personaRepository
                    .findById(studentInputDto.getId_persona())
                    .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR));

            oldPersona.setStudent(null);
            updatedPersona.setStudent(currentStudent);
            currentStudent.setPersona(updatedPersona);
        }

        if(studentInputDto.getId_teacher() != null){
            Teacher updatedTeacher = teacherRepository
                    .findById(studentInputDto.getId_teacher())
                    .orElseThrow(() -> new EntityNotFoundException(ID_TEACHER_ERROR));

            if(currentStudent.getTeacher() != null && !Objects.equals(currentStudent.getTeacher().getId_teacher(), studentInputDto.getId_teacher())){
                Teacher oldTeacher = teacherRepository
                        .findById(currentStudent.getTeacher().getId_teacher())
                        .orElseThrow(() -> new EntityNotFoundException(ID_TEACHER_ERROR));

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
                .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR));

        Integer id_persona = deletedStudent.getPersona().getId_persona();

        personaRepository.findById(id_persona)
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR))
                .setStudent(null);

        if (deletedStudent.getTeacher() != null)
            teacherRepository.findById(deletedStudent.getTeacher().getId_teacher())
                    .orElseThrow(() -> new EntityNotFoundException(ID_TEACHER_ERROR))
                    .getStudents()
                    .remove(deletedStudent);

        studentRepository.delete(deletedStudent);
    }
}
