package com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class StudentSubjectOutputDto {
    String id_student;
    String id_subject;
    String comments;
    Date initial_date;
    Date finish_date;
}