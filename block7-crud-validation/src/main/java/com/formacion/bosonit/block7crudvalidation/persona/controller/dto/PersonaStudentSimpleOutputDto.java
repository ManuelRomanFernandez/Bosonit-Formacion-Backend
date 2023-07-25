package com.formacion.bosonit.block7crudvalidation.persona.controller.dto;

import lombok.*;

@Getter
@Setter
public class PersonaStudentSimpleOutputDto extends PersonaSimpleOutputDto {
    private String id_student;
    private Integer num_hours_week;
    private String comments;
    private String id_student_teacher;
    private String branch;
}
