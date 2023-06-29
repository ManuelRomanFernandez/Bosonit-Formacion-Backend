package com.formacion.bosonit.block7crudvalidation.student_subject.application;

import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;

public interface StudentSubjectService {
    StudentSubjectOutputDto getStudentSubjectById(String id_student_subject);
    Iterable<StudentSubjectOutputDto> getStudentSubjectsByStudentId(String id_student);
    Iterable<StudentSubjectOutputDto> getAllStudentSubjects(Integer pageNumber, Integer pageSize);
    StudentSubjectOutputDto addStudentSubject(StudentSubjectInputDto studentSubjectInputDto);
    StudentSubjectOutputDto updateStudentSubjectById(StudentSubjectInputDto studentSubjectInputDto);
    void deleteStudentSubjectById(String id_student_subject);

}
