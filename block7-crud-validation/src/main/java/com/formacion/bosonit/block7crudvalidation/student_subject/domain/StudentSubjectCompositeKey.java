package com.formacion.bosonit.block7crudvalidation.student_subject.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectCompositeKey implements Serializable {
    private String student;
    private String subject;
}