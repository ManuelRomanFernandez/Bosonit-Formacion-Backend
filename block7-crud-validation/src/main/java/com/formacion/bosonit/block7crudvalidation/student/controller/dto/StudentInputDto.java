package com.formacion.bosonit.block7crudvalidation.student.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class StudentInputDto {
    String id_student;
    Integer id_persona;
    @NotNull(message = "num_hours_week no puede ser null")
    Integer num_hours_week = 0;
    String comments;
    String id_teacher;
    @NotNull(message = "branch no puede ser null")
    String branch = "N/A";
}
