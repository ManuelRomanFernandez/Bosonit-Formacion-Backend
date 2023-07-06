package com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubjectInputDto {
    String id_student;
    String id_subject;
    String comments;
    @NotNull(message = "initial_date no puede ser null")
    Date initial_date;
    Date finish_date;
}
