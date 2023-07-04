package com.formacion.bosonit.block7crudvalidation.teacher.application;

import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;

public interface TeacherService {
    TeacherFullOutputDto getFullTeacherById(String id_professor);
    TeacherSimpleOutputDto getSimpleTeacherById(String id_professor);
    Iterable<TeacherFullOutputDto> getAllFullTeachers(Integer pageNumber, Integer pageSize);
    Iterable<TeacherSimpleOutputDto> getAllSimpleTeachers(Integer pageNumber, Integer pageSize);
    TeacherFullOutputDto addTeachers(TeacherInputDto teacherInputDto);
    TeacherSimpleOutputDto updateTeacherById(TeacherInputDto teacherInputDto);
    void deleteTeacherById(String id_professor);
}
