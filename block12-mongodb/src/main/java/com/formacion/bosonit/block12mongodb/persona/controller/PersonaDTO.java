package com.formacion.bosonit.block12mongodb.persona.controller;

import com.formacion.bosonit.block12mongodb.persona.domain.Persona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO {
    @Id
    private Integer persona_id;
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

    public PersonaDTO (Persona persona){
        this.persona_id = persona.getPersona_id();
        this.usuario = persona.getUsuario();
        this.password = persona.getPassword();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email = persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.getActive();
        this.created_date = persona.getCreated_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
    }

    public Persona personaDtoToPersona(PersonaDTO personaDTO){
        return new Persona(
                personaDTO.getPersona_id(),
                personaDTO.getUsuario(),
                personaDTO.getPassword(),
                personaDTO.getName(),
                personaDTO.getSurname(),
                personaDTO.getCompany_email(),
                personaDTO.getPersonal_email(),
                personaDTO.getCity(),
                personaDTO.getActive(),
                personaDTO.getCreated_date(),
                personaDTO.getImagen_url(),
                personaDTO.getTermination_date()
        );
    }
}
