package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaTeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaStudentOutputDto;

public interface PersonaService {
    PersonaOutputDto getPersonaById(Integer id);
    PersonaStudentOutputDto getPersonaStudentById(Integer id);
    PersonaTeacherOutputDto getPersonaTeacherById(Integer id);
    Iterable<PersonaOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario);
    Iterable<?> getFullPersonaStudentByUsuario(Integer pageNumber, Integer pageSize, String usuario);
    Iterable<PersonaOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize);
    Iterable<?> getAllFullPersonas(Integer pageNumber, Integer pageSize);
    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;
    PersonaOutputDto updatePersona(PersonaInputDto persona, Integer id);
    void deletePersonaById(Integer id);
}
