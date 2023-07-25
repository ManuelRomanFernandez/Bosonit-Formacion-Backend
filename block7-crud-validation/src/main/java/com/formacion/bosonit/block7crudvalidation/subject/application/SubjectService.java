package com.formacion.bosonit.block7crudvalidation.subject.application;

import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectOutputDto;

import java.util.List;

public interface SubjectService {
    SubjectOutputDto getSubjectById(String id_subject);
    List<SubjectOutputDto> getAllSubjects(Integer pageNumber, Integer pageSize);
    SubjectOutputDto addSubject(SubjectInputDto subjectInputDto);
    SubjectOutputDto updateSubjectById(SubjectInputDto subjectInputDto);
    void deleteSubjectById(String id_subject);
}
