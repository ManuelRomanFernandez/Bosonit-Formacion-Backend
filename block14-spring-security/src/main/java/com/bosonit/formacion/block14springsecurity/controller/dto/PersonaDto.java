package com.bosonit.formacion.block14springsecurity.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class PersonaDto {
    Integer id_persona;
    @NotNull(message = "usuario no puede ser null")
    @Size(min = 5, max = 10, message = "usuario debe tener entre 5 y 10 caracteres")
    String usuario;
    @NotNull(message = "password no puede ser null")
    String password;
    @NotNull(message = "name no puede ser null")
    String name;
    String surname;
    @NotNull(message = "company_email no puede ser null")
    String company_email;
    @NotNull(message = "personal_email no puede ser null")
    String personal_email;
    @NotNull(message = "city no puede ser null")
    String city;
    @NotNull(message = "active no puede ser null")
    Boolean active;
    @NotNull(message = "created_date no puede ser null")
    Date created_date;
    String imagen_url;
    Date termination_date;
}