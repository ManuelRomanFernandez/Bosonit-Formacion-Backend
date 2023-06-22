package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDto;

public interface PersonaService {
    PersonaOutputDto getPersonaById(Integer id);
    Iterable<PersonaOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario);
    Iterable<PersonaOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize);
    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;
    PersonaOutputDto updatePersona(PersonaInputDto persona);
    void deletePersonaById(Integer id);
}
