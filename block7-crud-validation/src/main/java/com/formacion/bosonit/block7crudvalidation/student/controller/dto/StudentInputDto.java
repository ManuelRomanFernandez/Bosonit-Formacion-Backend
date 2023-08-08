package com.formacion.bosonit.block7crudvalidation.student.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class StudentInputDto {
    private String id_student;
    private Integer id_persona;
    @NotNull(message = "num_hours_week no puede ser null")
    Integer num_hours_week = 0;
    private String comments;
    private String id_teacher;
    @NotNull(message = "branch no puede ser null")
    private String branch = "N/A";
}
