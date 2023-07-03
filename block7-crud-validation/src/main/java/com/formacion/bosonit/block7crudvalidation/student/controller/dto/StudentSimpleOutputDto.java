package com.formacion.bosonit.block7crudvalidation.student.controller.dto;

import lombok.*;

@Getter
@Setter
public class StudentSimpleOutputDto {
    String id_student;
    Integer id_persona;
    Integer num_hours_week;
    String comments;
    String id_teacher;
    String branch;
}
