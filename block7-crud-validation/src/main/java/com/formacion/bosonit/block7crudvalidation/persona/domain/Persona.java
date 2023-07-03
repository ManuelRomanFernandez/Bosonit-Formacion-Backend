package com.formacion.bosonit.block7crudvalidation.persona.domain;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.exception.UnprocessableEntityException;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "persona")
@NoArgsConstructor
@Getter
@Setter
public class Persona {
    @Id
    @GeneratedValue
    public Integer id_persona;
    public String usuario;
    public String password;
    @Column(name = "nombre")
    public String name;
    @Column(name = "apellido")
    public String surname;
    @Column(name = "email_corporativo")
    public String company_email;
    @Column(name = "email_personal")
    public String personal_email;
    @Column(name = "ciudad")
    public String city;
    @Column(name = "activo")
    public Boolean active;
    public Date created_date;
    public String imagen;
    public Date termination_date;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public Student student;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public Teacher teacher;

    public Persona(PersonaInputDto personaInputDto) throws UnprocessableEntityException {
        this.id_persona = personaInputDto.getId_persona();

        if(personaInputDto.getUsuario() == null){
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        }
        else if(personaInputDto.getUsuario().length() > 10){
            throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
        }
        else if(personaInputDto.getUsuario().length() < 5){
            throw new UnprocessableEntityException("Longitud de usuario no puede ser inferior a 5 caracteres");
        }
        else{
            this.usuario = personaInputDto.getUsuario();
        }

        if(personaInputDto.getPassword() == null){
            throw new UnprocessableEntityException("Password no puede ser nulo");
        }
        else{
            this.password = personaInputDto.getPassword();
        }

        if(personaInputDto.getName() == null){
            throw new UnprocessableEntityException("Name no puede ser nulo");
        }
        else{
            this.name = personaInputDto.getName();
        }

        this.surname = personaInputDto.getSurname();

        if(personaInputDto.getCompany_email() == null){
            throw new UnprocessableEntityException("Company_Email no puede ser nulo");
        }
        else{
            this.company_email = personaInputDto.getCompany_email();
        }

        if(personaInputDto.getPersonal_email() == null){
            throw new UnprocessableEntityException("Personal_Email no puede ser nulo");
        }
        else{
            this.personal_email = personaInputDto.getPersonal_email();
        }

        if(personaInputDto.getCity() == null){
            throw new UnprocessableEntityException("City no puede ser nulo");
        }
        else{
            this.city = personaInputDto.getCity();
        }

        if(personaInputDto.getActive() == null){
            throw new UnprocessableEntityException("Active no puede ser nulo");
        }
        else{
            this.active = personaInputDto.getActive();
        }

        if(personaInputDto.getCreated_date() == null){
            throw new UnprocessableEntityException("Created_date no puede ser nulo");
        }
        else{
            this.created_date = personaInputDto.getCreated_date();
        }

        this.imagen = personaInputDto.getImagen();

        this.termination_date = personaInputDto.getTermination_date();
    }
}
