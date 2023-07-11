package com.formacion.bosonit.block7crudvalidation.persona.controller.dto;

import lombok.*;

@Getter
@Setter
public class PersonaTeacherSimpleOutputDto extends PersonaSimpleOutputDto {
    String id_teacher;
    String comments;
    String branch;
}
