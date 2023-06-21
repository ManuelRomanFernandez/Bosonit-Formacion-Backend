package com.formacion.bosonit.block7crudvalidation.persona.domain;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue
    private Integer id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen;
    private Date termination_date;

    public Persona(PersonaInputDto personaInputDto) throws Exception {
        if(personaInputDto.getUsuario() == null){
            throw new Exception("Usuario no puede ser nulo");
        }
        else if(personaInputDto.getUsuario().length() > 10){
            throw new Exception("Longitud de usuario no puede ser superior a 10 caracteres");
        }
        else if(personaInputDto.getUsuario().length() < 5){
            throw new Exception("Longitud de usuario no puede ser inferior a 5 caracteres");
        }
        else{
            this.usuario = personaInputDto.getUsuario();
        }

        if(personaInputDto.getPassword() == null){
            throw new Exception("Password no puede ser nulo");
        }
        else{
            this.password = personaInputDto.getPassword();
        }

        if(personaInputDto.getName() == null){
            throw new Exception("Name no puede ser nulo");
        }
        else{
            this.name = personaInputDto.getName();
        }

        this.surname = personaInputDto.getSurname();

        if(personaInputDto.getCompany_email() == null){
            throw new Exception("Company_Email no puede ser nulo");
        }
        else{
            this.company_email = personaInputDto.getCompany_email();
        }

        if(personaInputDto.getPersonal_email() == null){
            throw new Exception("Personal_Email no puede ser nulo");
        }
        else{
            this.personal_email = personaInputDto.getPersonal_email();
        }

        if(personaInputDto.getCity() == null){
            throw new Exception("City no puede ser nulo");
        }
        else{
            this.city = personaInputDto.getCity();
        }

        if(personaInputDto.getActive() == null){
            throw new Exception("Active no puede ser nulo");
        }
        else{
            this.active = personaInputDto.getActive();
        }

        if(personaInputDto.getCreated_date() == null){
            throw new Exception("Created_date no puede ser nulo");
        }
        else{
            this.created_date = personaInputDto.getCreated_date();
        }

        this.imagen = personaInputDto.getImagen();

        this.termination_date = personaInputDto.getTermination_date();
    }

    public PersonaOutputDto personaToPersonaOutputDto(){
        return new PersonaOutputDto(
            this.id_persona,
            this.usuario,
            this.password,
            this.name,
            this.surname,
            this.company_email,
            this.personal_email,
            this.city,
            this.active,
            this.created_date,
            this.imagen,
            this.termination_date
        );
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getTermination_date() {
        return termination_date;
    }

    public void setTermination_date(Date termination_date) {
        this.termination_date = termination_date;
    }
}
