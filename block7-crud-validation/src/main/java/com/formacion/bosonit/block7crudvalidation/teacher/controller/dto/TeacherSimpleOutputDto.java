package com.formacion.bosonit.block7crudvalidation.teacher.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSimpleOutputDto {
    String id_teacher;
    Integer id_persona;
    String comments;
    String branch;
}
