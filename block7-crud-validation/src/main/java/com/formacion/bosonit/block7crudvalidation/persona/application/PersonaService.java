package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.*;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;

import java.util.HashMap;
import java.util.List;

public interface PersonaService {
    PersonaSimpleOutputDto getPersonaById(Integer id);
    Object getFullPersonaById(Integer id);
    List<PersonaSimpleOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario);
    List<Object> getFullPersonasByUsuario(Integer pageNumber, Integer pageSize, String usuario);
    List<PersonaSimpleOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize);
    List<Object> getAllFullPersonas(Integer pageNumber, Integer pageSize);
    TeacherSimpleOutputDto getTemplateTeacher(String id_teacher);
    List<PersonaSimpleOutputDto> getCustomQuery(HashMap<String, Object> options);
    PersonaSimpleOutputDto addPersona(PersonaInputDto persona);
    PersonaSimpleOutputDto updatePersona(PersonaInputDto persona, Integer id);
    void deletePersonaById(Integer id);
}
