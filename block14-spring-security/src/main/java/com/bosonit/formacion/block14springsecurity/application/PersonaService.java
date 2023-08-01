package com.bosonit.formacion.block14springsecurity.application;

import com.bosonit.formacion.block14springsecurity.controller.dto.PersonaDto;

import java.util.List;

public interface PersonaService {
    PersonaDto getPersonaById(int id_persona);
    PersonaDto getPersonaByUsuario(String usuario);
    List<PersonaDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaDto addPersona(PersonaDto dto);
    PersonaDto updatePersonaById(int persona_id, PersonaDto dto);
    void deletePersonaById(int id_persona);
}
