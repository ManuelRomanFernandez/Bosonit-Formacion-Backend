package com.formacion.bosonit.block7crud.application;

import com.formacion.bosonit.block7crud.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crud.controller.dto.PersonaOutputDto;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto updatePersona(PersonaInputDto persona);

    PersonaOutputDto deletePersona(int id);

    PersonaOutputDto getPersonaById(int id);

    Iterable<PersonaOutputDto> getPersonaByName(int pageNumber, int pageSize, String nombre);

    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);

}
