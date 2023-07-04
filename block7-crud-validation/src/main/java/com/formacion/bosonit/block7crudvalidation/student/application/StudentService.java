package com.formacion.bosonit.block7crudvalidation.student.application;

import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;

public interface StudentService {
    StudentFullOutputDto getFullStudentById(String id_student);
    StudentSimpleOutputDto getSimpleStudentById(String id_student);
    Iterable<StudentFullOutputDto> getAllFullStudents(Integer pageNumber, Integer pageSize);
    Iterable<StudentSimpleOutputDto> getAllSimpleStudents(Integer pageNumber, Integer pageSize);
    StudentFullOutputDto addStudent(StudentInputDto student);
    StudentSimpleOutputDto updateStudentById(StudentInputDto student);
    void deleteStudentById(String id_student);
}
