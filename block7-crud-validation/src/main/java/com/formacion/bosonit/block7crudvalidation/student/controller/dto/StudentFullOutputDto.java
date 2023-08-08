package com.formacion.bosonit.block7crudvalidation.student.controller.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class StudentFullOutputDto extends StudentSimpleOutputDto {
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
}
