package com.formacion.bosonit.block7crudvalidation.student.application;

import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;

public interface StudentService {
    StudentOutputDto getFullStudentById(String id_student);
    StudentSimpleOutputDto getSimpleStudentById(String id_student);
    Iterable<StudentOutputDto> getAllFullStudents(Integer pageNumber, Integer pageSize);
    Iterable<StudentSimpleOutputDto> getAllSimpleStudents(Integer pageNumber, Integer pageSize);
    StudentOutputDto addStudent(StudentInputDto student);
    StudentSimpleOutputDto updateStudentById(StudentInputDto student);
    void deleteStudentById(String id_student);
}
