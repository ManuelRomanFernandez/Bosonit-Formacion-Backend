package com.formacion.bosonit.block7crudvalidation.student.application;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student_subject.repository.StudentSubjectRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.teacher.respository.TeacherRepository;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentSubjectRepository studentSubjectRepository;

    @Override
    public StudentOutputDto getFullStudentById(String id_student) {
        return studentRepository.findAll()
                .stream()
                .filter(e -> e.getId_student().equals(id_student))
                .findFirst().orElseThrow().studentToStudentOutputDto();
    }

    @Override
    public StudentSimpleOutputDto getSimpleStudentById(String id_student) {
        return studentRepository.findAll()
                .stream()
                .filter(e -> e.getId_student().equals(id_student))
                .findFirst().orElseThrow().studentToStudentSimpleOutputDto();
    }

    @Override
    public Iterable<StudentOutputDto> getAllFullStudents(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(Student::studentToStudentOutputDto)
                .toList();
    }

    @Override
    public Iterable<StudentSimpleOutputDto> getAllSimpleStudents(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(Student::studentToStudentSimpleOutputDto)
                .toList();
    }

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) {
        Persona persona = personaRepository.findById(studentInputDto.getId_persona()).orElseThrow();
        Student student = new Student(studentInputDto);

        persona.setStudent(student);
        student.setPersona(persona);

        if(studentInputDto.getId_teacher() != null){
            Teacher teacher = teacherRepository.findById(studentInputDto.getId_teacher()).orElseThrow();

            teacher.getStudents().add(student);
            student.setTeacher(teacher);
        }

        return studentRepository.save(student).studentToStudentOutputDto();
    }

    @Override
    public StudentSimpleOutputDto updateStudentById(StudentInputDto studentInputDto) {
        Student currentStudent = studentRepository.findAll()
                .stream()
                .filter(e -> e.getId_student().equals(studentInputDto.getId_student()))
                .findFirst().orElseThrow();


        currentStudent.setNum_hours_week(studentInputDto.getNum_hours_week());

        if(studentInputDto.getComments() != null)
            currentStudent.setComments(studentInputDto.getComments());

        currentStudent.setBranch(studentInputDto.getBranch());

        if(studentInputDto.getId_persona() != null){
            if(!Objects.equals(currentStudent.getPersona().getId_persona(), studentInputDto.getId_persona())){
                Persona oldPersona = personaRepository.findById(currentStudent.getPersona().getId_persona()).orElseThrow();
                Persona updatedPersona = personaRepository.findById(studentInputDto.getId_persona()).orElseThrow();

                oldPersona.setStudent(null);
                updatedPersona.setStudent(currentStudent);
                currentStudent.setPersona(updatedPersona);
            }
        }

        if(studentInputDto.getId_teacher() != null){
            Teacher updatedTeacher = teacherRepository
                    .findById(studentInputDto.getId_teacher())
                    .orElseThrow();

            if(currentStudent.getTeacher() != null && !Objects.equals(currentStudent.getTeacher().getId_teacher(), studentInputDto.getId_teacher())){
                Teacher oldTeacher = teacherRepository
                        .findById(currentStudent.getTeacher().getId_teacher())
                        .orElseThrow();

                oldTeacher.getStudents().remove(currentStudent);
            }

            updatedTeacher.getStudents().add(currentStudent);
            currentStudent.setTeacher(updatedTeacher);
        }

        return studentRepository.save(currentStudent)
                .studentToStudentSimpleOutputDto();
    }

    @Override
    public void deleteStudentById(String id_student) {
        Student deletedStudent = studentRepository.findAll()
                .stream()
                .filter(e -> e.getId_student().equals(id_student))
                .findFirst()
                .orElseThrow();

        personaRepository.findById(deletedStudent.getPersona().getId_persona())
                .orElseThrow()
                .setStudent(null);

        if (deletedStudent.getTeacher() != null)
            teacherRepository.findById(deletedStudent.getTeacher().getId_teacher())
                    .orElseThrow()
                    .getStudents()
                    .remove(deletedStudent);

        if(deletedStudent.getStudentSubjects().toArray().length > 0)
            studentSubjectRepository.findAll()
                    .stream()
                    .map(e -> e.getStudent().getId_student());

        studentRepository.delete(deletedStudent);
    }
}
