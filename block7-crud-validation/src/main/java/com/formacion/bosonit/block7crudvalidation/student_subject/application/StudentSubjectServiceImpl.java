package com.formacion.bosonit.block7crudvalidation.student_subject.application;

import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.mapper.StudentSubjectMapper;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import com.formacion.bosonit.block7crudvalidation.student_subject.repository.StudentSubjectRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {
    StudentSubjectMapper mapper = Mappers.getMapper(StudentSubjectMapper.class);
    @Autowired
    StudentSubjectRepository studentSubjectRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentSubjectOutputDto getStudentSubjectById(String id_student_subject) {
        return mapper.studentSubjectToStudentSubjectOutputDto(
                studentSubjectRepository
                        .findById(id_student_subject)
                        .orElseThrow(() -> new EntityNotFoundException("No existe la asignatura con el id indicado")));
    }

    @Override
    public Iterable<StudentSubjectOutputDto> getStudentSubjectsByStudentId(String id_student) {
        return studentSubjectRepository.findAll()
                .stream()
                .filter(e -> e.getStudent().getId_student().equals(id_student))
                .map(studentSubject -> mapper.studentSubjectToStudentSubjectOutputDto(studentSubject))
                .toList();
    }

    @Override
    public Iterable<StudentSubjectOutputDto> getAllStudentSubjects(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentSubjectRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(studentSubject -> mapper.studentSubjectToStudentSubjectOutputDto(studentSubject))
                .toList();
    }

    @Override
    public StudentSubjectOutputDto addStudentSubject(StudentSubjectInputDto studentSubjectInputDto) {
        StudentSubject studentSubject = mapper.studentSubjectInputDtoToStudentSubject(studentSubjectInputDto);
        Student student = studentRepository.findById(studentSubjectInputDto.getId_student()).orElseThrow();

        student.getStudentSubjects().add(studentSubject);
        studentSubject.setStudent(student);

        return mapper.studentSubjectToStudentSubjectOutputDto(studentSubjectRepository.save(studentSubject));
    }

    @Override
    public StudentSubjectOutputDto updateStudentSubjectById(StudentSubjectInputDto studentSubjectInputDto) {
        StudentSubject currentStudentSubject = studentSubjectRepository
                .findById(studentSubjectInputDto.getId_student_subject())
                .orElseThrow(() -> new EntityNotFoundException("No existe la asignatura con el id indicado"));

        if(studentSubjectInputDto.getSubject() != null)
            currentStudentSubject.setSubject(studentSubjectInputDto.getSubject());

        if(studentSubjectInputDto.getComments() != null)
            currentStudentSubject.setComments(studentSubjectInputDto.getComments());

        currentStudentSubject.setInitial_date(studentSubjectInputDto.getInitial_date());

        if(studentSubjectInputDto.getFinish_date() != null)
            currentStudentSubject.setFinish_date(studentSubjectInputDto.getFinish_date());

        if(studentSubjectInputDto.getId_student() != null){
            Student updatedStudent = studentRepository
                    .findById(studentSubjectInputDto.getId_student())
                    .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado"));

            if(!Objects.equals(
                    currentStudentSubject.getStudent().getId_student(),
                    studentSubjectInputDto.getId_student())
            ){
                Student oldStudent = studentRepository
                        .findById(currentStudentSubject.getStudent().getId_student())
                        .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado"));


                oldStudent.getStudentSubjects().remove(currentStudentSubject);
            }

            updatedStudent.getStudentSubjects().add(currentStudentSubject);
            currentStudentSubject.setStudent(updatedStudent);
        }

        return mapper.studentSubjectToStudentSubjectOutputDto( studentSubjectRepository.save(currentStudentSubject));
    }

    @Override
    public void deleteStudentSubjectById(String id_student_subject) {
        StudentSubject deleteStudentSubject = studentSubjectRepository
                .findById(id_student_subject)
                .orElseThrow(() -> new EntityNotFoundException("No existe la asignatura con el id indicado"));

        String id_student = deleteStudentSubject.getStudent().getId_student();

        studentRepository.findById(id_student)
                .orElseThrow()
                .getStudentSubjects()
                .remove(deleteStudentSubject);

        studentSubjectRepository.delete(deleteStudentSubject);
    }
}
