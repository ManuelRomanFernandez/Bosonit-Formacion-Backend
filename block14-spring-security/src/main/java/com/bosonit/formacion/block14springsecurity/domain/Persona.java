package com.bosonit.formacion.block14springsecurity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
@NoArgsConstructor
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue
    private Integer id_persona;
    @Column(unique = true)
    private String usuario;
    private String password;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String surname;
    @Column(name = "email_corporativo")
    private String company_email;
    @Column(name = "email_personal")
    private String personal_email;
    @Column(name = "ciudad")
    private String city;
    @Column(name = "activo")
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private boolean admin;
}
