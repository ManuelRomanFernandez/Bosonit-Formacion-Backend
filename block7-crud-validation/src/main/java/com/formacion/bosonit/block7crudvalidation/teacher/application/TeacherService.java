package com.formacion.bosonit.block7crudvalidation.teacher.application;

import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;

import java.util.List;

public interface TeacherService {
    TeacherFullOutputDto getFullTeacherById(String id_teacher);
    TeacherSimpleOutputDto getSimpleTeacherById(String id_teacher);
    List<TeacherFullOutputDto> getAllFullTeachers(Integer pageNumber, Integer pageSize);
    List<TeacherSimpleOutputDto> getAllSimpleTeachers(Integer pageNumber, Integer pageSize);
    TeacherFullOutputDto addTeachers(TeacherInputDto teacherInputDto);
    TeacherSimpleOutputDto updateTeacherById(TeacherInputDto teacherInputDto);
    void deleteTeacherById(String id_teacher);
}
