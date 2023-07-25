package com.formacion.bosonit.block7crudvalidation.teacher.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class TeacherInputDto {
    private String id_teacher;
    private Integer id_persona;
    private String comments;
    @NotNull(message = "branch no puede ser null")
    private String branch = "N/A";
}
