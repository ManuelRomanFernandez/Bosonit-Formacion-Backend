package com.formacion.bosonit.block7crudvalidation.teacher.controller.dto;

import lombok.*;

@Getter
@Setter
public class TeacherSimpleOutputDto {
    private String id_teacher;
    private Integer id_persona;
    private String comments;
    private String branch;
}
