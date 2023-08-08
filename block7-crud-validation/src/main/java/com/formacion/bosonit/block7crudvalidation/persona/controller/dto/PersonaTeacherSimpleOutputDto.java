package com.formacion.bosonit.block7crudvalidation.persona.controller.dto;

import lombok.*;

@Getter
@Setter
public class PersonaTeacherSimpleOutputDto extends PersonaSimpleOutputDto {
    private String id_teacher;
    private String comments;
    private String branch;
}
