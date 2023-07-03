package com.formacion.bosonit.block7crudvalidation.student.controller.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class StudentOutputDto {
    String id_student;
    Integer id_persona;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen;
    Date termination_date;
    Integer num_hours_week;
    String comments;
    String id_teacher;
    String branch;
}
