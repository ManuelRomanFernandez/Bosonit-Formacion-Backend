package com.formacion.bosonit.block7crudvalidation.teacher.controller.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class TeacherFullOutputDto extends TeacherSimpleOutputDto {
    private String usuario;
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
