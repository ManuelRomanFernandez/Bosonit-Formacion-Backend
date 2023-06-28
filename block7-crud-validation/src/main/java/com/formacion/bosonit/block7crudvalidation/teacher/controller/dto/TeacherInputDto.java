package com.formacion.bosonit.block7crudvalidation.teacher.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInputDto {
    String id_teacher;
    @NotNull(message = "id_persona no puede ser null")
    Integer id_persona;
    String comments;
    @NotNull(message = "branch no puede ser null")
    String branch = "N/A";
}
