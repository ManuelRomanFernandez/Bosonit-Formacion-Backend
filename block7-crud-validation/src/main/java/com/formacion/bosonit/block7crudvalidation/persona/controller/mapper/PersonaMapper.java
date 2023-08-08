package com.formacion.bosonit.block7crudvalidation.persona.controller.mapper;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaStudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaTeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = Persona.class)
public interface PersonaMapper {

    PersonaSimpleOutputDto personaToPersonaOutDto(Persona persona);

    @Mapping(target = "id_student", source = "persona.student.id_student")
    @Mapping(target = "num_hours_week", source = "persona.student.num_hours_week")
    @Mapping(target = "comments", source = "persona.student.comments")
    @Mapping(target = "id_student_teacher", source = "persona.student.teacher.id_teacher")
    @Mapping(target = "branch", source = "persona.student.branch")
    PersonaStudentSimpleOutputDto personaToPersonaStudentOutPut(Persona persona);

    @Mapping(target = "id_teacher", source = "persona.teacher.id_teacher")
    @Mapping(target = "comments", source = "persona.teacher.comments")
    @Mapping(target = "branch", source = "persona.teacher.branch")
    PersonaTeacherSimpleOutputDto personaToPersonaTeacherOutPut(Persona persona);
}
