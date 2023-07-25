package com.formacion.bosonit.block7crudvalidation.student.controller.dto;

import lombok.*;

@Getter
@Setter
public class StudentSimpleOutputDto {
    private String id_student;
    private Integer id_persona;
    private Integer num_hours_week;
    private String comments;
    private String id_teacher;
    private String branch;
}
