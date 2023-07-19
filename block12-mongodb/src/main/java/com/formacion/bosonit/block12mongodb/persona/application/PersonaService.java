package com.formacion.bosonit.block12mongodb.persona.application;

import com.formacion.bosonit.block12mongodb.persona.controller.PersonaDTO;

import java.util.List;

public interface PersonaService {
    PersonaDTO getPersonaById(Integer persona_id);
    List<PersonaDTO> getAllPersonas(Integer pageNumber, Integer pageSize);
    PersonaDTO addPersona(PersonaDTO personaDTO);
    PersonaDTO updatePersonaById(Integer id, PersonaDTO personaDTO);
    void deletePersonaById(Integer persona_id);
}
