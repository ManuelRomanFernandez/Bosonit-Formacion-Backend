package com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubjectOutputDto {
    String id_student;
    String id_subject;
    String comments;
    Date initial_date;
    Date finish_date;
}