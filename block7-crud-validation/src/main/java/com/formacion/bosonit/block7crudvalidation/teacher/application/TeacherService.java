package com.formacion.bosonit.block7crudvalidation.teacher.application;

import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;

public interface TeacherService {
    TeacherOutputDto getFullTeacherById(String id_professor);
    TeacherSimpleOutputDto getSimpleTeacherById(String id_professor);
    Iterable<TeacherOutputDto> getAllFullTeachers(Integer pageNumber, Integer pageSize);
    Iterable<TeacherSimpleOutputDto> getAllSimpleTeachers(Integer pageNumber, Integer pageSize);
    TeacherOutputDto addTeachers(TeacherInputDto teacherInputDto);
    TeacherSimpleOutputDto updateTeacherById(TeacherInputDto teacherInputDto);
    void deleteTeacherById(String id_professor);
}
