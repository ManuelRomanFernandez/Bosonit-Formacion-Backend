package com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.mapper;

import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = Teacher.class)
public interface TeacherMapper {

    @Mapping(target = "id_persona", source = "teacher.persona.id_persona")
    @Mapping(target = "usuario", source = "teacher.persona.usuario")
    @Mapping(target = "name", source = "teacher.persona.name")
    @Mapping(target = "surname", source = "teacher.persona.surname")
    @Mapping(target = "company_email", source = "teacher.persona.company_email")
    @Mapping(target = "personal_email", source = "teacher.persona.personal_email")
    @Mapping(target = "city", source = "teacher.persona.city")
    @Mapping(target = "active", source = "teacher.persona.active")
    @Mapping(target = "created_date", source = "teacher.persona.created_date")
    @Mapping(target = "imagen", source = "teacher.persona.imagen")
    @Mapping(target = "termination_date", source = "teacher.persona.termination_date")
    TeacherFullOutputDto teacherToTeacherOutputDto(Teacher teacher);

    @Mapping(target = "id_persona", source = "teacher.persona.id_persona")
    TeacherSimpleOutputDto teacherToTeacherSimpleOutputDto(Teacher teacher);

    Teacher teacherInputDtoToTeacher(TeacherInputDto teacherInputDto);
}
