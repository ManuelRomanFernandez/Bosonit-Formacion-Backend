package com.formacion.bosonit.block7crudvalidation.persona.controller.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class PersonaInputDto {
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
    String imagen_url;
    Date termination_date;
}
