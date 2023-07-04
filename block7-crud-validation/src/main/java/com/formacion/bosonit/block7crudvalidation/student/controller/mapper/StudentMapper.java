package com.formacion.bosonit.block7crudvalidation.student.controller.mapper;

import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = Student.class)
public interface StudentMapper {

    @Mapping(target = "id_persona", source = "student.persona.id_persona")
    @Mapping(target = "usuario", source = "student.persona.usuario")
    @Mapping(target = "name", source = "student.persona.name")
    @Mapping(target = "surname", source = "student.persona.surname")
    @Mapping(target = "company_email", source = "student.persona.company_email")
    @Mapping(target = "personal_email", source = "student.persona.personal_email")
    @Mapping(target = "city", source = "student.persona.city")
    @Mapping(target = "active", source = "student.persona.active")
    @Mapping(target = "created_date", source = "student.persona.created_date")
    @Mapping(target = "imagen", source = "student.persona.imagen")
    @Mapping(target = "termination_date", source = "student.persona.termination_date")
    @Mapping(target = "id_teacher", source = "student.teacher.id_teacher")
    StudentFullOutputDto studentToStudentOutputDto(Student student);

    @Mapping(target = "id_persona", source = "student.persona.id_persona")
    @Mapping(target = "id_teacher", source = "student.teacher.id_teacher")
    StudentSimpleOutputDto studentToStudentSimpleOutputDto(Student student);

    Student studentInputDtoToTeacher(StudentInputDto studentInputDto);
}
