package com.formacion.bosonit.block7crudvalidation.persona.controller.dto;

import lombok.*;

@Getter
@Setter
public class PersonaStudentSimpleOutputDto extends PersonaSimpleOutputDto {
    String id_student;
    Integer num_hours_week;
    String comments;
    String id_student_teacher;
    String branch;
}
