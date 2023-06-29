package com.formacion.bosonit.block7crudvalidation.student_subject.controller.mapper;

import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = StudentSubject.class)
public interface StudentSubjectMapper {

    @Mapping(target = "id_student", source = "studentSubject.student.id_student")
    StudentSubjectOutputDto studentSubjectToStudentSubjectOutputDto(StudentSubject studentSubject);

    StudentSubject studentSubjectInputDtoToStudentSubject(StudentSubjectInputDto studentSubjectInputDto);
}
