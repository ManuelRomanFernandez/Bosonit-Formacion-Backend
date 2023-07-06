package com.formacion.bosonit.block7crudvalidation.subject.controller.mapper;

import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import com.formacion.bosonit.block7crudvalidation.subject.domain.Subject;
import org.mapstruct.Mapper;

@Mapper(uses = Subject.class)
public interface SubjectMapper {
    SubjectOutputDto subjectToSubjectOutputDto(Subject subject);
    Subject subjectInputDtoToSubject(SubjectInputDto subjectInputDto);
}
