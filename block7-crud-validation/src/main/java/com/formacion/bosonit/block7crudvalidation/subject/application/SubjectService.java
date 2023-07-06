package com.formacion.bosonit.block7crudvalidation.subject.application;

import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectOutputDto;

public interface SubjectService {
    SubjectOutputDto getSubjectById(String id_subject);
    Iterable<SubjectOutputDto> getAllSubjects(Integer pageNumber, Integer pageSize);
    SubjectOutputDto addSubject(SubjectInputDto subjectInputDto);
    SubjectOutputDto updateSubjectById(SubjectInputDto subjectInputDto);
    void deleteSubjectById(String subject_id);
}
