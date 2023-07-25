package com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class StudentSubjectInputDto {
    private String id_student;
    private String id_subject;
    private String comments;
    @NotNull(message = "initial_date no puede ser null")
    private Date initial_date;
    private Date finish_date;
}
