package com.formacion.bosonit.block7crud.domain;

import com.formacion.bosonit.block7crud.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crud.controller.dto.PersonaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    Integer id;
    String nombre;
    Integer edad;
    String poblacion;

    public Persona(PersonaInputDto personaInputDto){
        this.id = personaInputDto.getId();
        this.nombre = personaInputDto.getNombre();
        this.edad = personaInputDto.getEdad();
        this.poblacion = personaInputDto.getPoblacion();
    }

    public PersonaOutputDto personaToPersonaOutputDto(){
        return new PersonaOutputDto(
            this.id,
            this.nombre,
            this.edad,
            this.poblacion
        );
    }
}
