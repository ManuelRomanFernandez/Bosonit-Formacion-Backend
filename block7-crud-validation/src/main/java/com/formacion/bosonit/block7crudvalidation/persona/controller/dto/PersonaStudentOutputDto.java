package com.formacion.bosonit.block7crudvalidation.persona.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaStudentOutputDto {
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
    String id_student;
    Integer num_hours_week;
    String comments;
    String id_student_teacher;
    String branch;
}
