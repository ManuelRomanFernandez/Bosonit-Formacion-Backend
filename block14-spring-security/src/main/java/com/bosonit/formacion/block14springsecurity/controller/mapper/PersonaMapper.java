package com.bosonit.formacion.block14springsecurity.controller.mapper;

import com.bosonit.formacion.block14springsecurity.controller.dto.PersonaDto;
import com.bosonit.formacion.block14springsecurity.domain.Persona;
import org.mapstruct.Mapper;

@Mapper(uses = Persona.class)
public interface PersonaMapper {
    PersonaDto personaToPersonaDto(Persona persona);
    Persona personaDtoToPersona(PersonaDto dto);
}
