package com.formacion.bosonit.block7crudvalidation.student_subject.application;

import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;

import java.util.List;

public interface StudentSubjectService {
    StudentSubjectOutputDto getStudentSubjectByIds(String id_student, String id_subject);
    Iterable<StudentSubjectOutputDto> getAllStudentSubjects(Integer pageNumber, Integer pageSize);
    Iterable<StudentSubjectOutputDto> getStudentSubjectsByStudentId(String id_student);
    Iterable<StudentSubjectOutputDto> getStudentSubjectsBySubjectId(String id_subject);
    StudentSubjectOutputDto addStudentSubject(StudentSubjectInputDto studentSubjectInputDto);
    void addMultipleStudentSubjectsByStudentId(String id_student, List<String> subjects);
    StudentSubjectOutputDto updateStudentSubjectById(StudentSubjectInputDto studentSubjectInputDto);
    void deleteStudentSubjectById(String id_student, String id_subject);
    void deleteStudentSubjectsByStudentId(String id_student, List<String> subjects);
}
