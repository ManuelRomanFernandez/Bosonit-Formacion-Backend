package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.*;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaStudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;

public interface PersonaService {
    PersonaSimpleOutputDto getPersonaById(Integer id);
    Object getFullPersonaById(Integer id);
    PersonaStudentSimpleOutputDto getPersonaStudentById(Integer id);
    PersonaTeacherSimpleOutputDto getPersonaTeacherById(Integer id);
    Iterable<PersonaSimpleOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario);
    Iterable<?> getFullPersonaStudentByUsuario(Integer pageNumber, Integer pageSize, String usuario);
    Iterable<PersonaSimpleOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize);
    Iterable<?> getAllFullPersonas(Integer pageNumber, Integer pageSize);
    TeacherSimpleOutputDto getTemplateTeacher(String id_teacher);
    PersonaSimpleOutputDto addPersona(PersonaInputDto persona);
    PersonaSimpleOutputDto updatePersona(PersonaInputDto persona, Integer id);
    void deletePersonaById(Integer id);
}
