package com.formacion.bosonit.block7crudvalidation.subject.application;

import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.mapper.SubjectMapper;
import com.formacion.bosonit.block7crudvalidation.subject.domain.Subject;
import com.formacion.bosonit.block7crudvalidation.subject.repository.SubjectRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{
    SubjectMapper mapper = Mappers.getMapper(SubjectMapper.class);
    @Autowired
    SubjectRepository subjectRepository;
    private static final String ID_SUBJECT_ERROR = "No existe la asignatura con el id indicado";

    @Override
    public SubjectOutputDto getSubjectById(String idSubject) {
        return mapper.subjectToSubjectOutputDto(
                subjectRepository.findById(idSubject)
                        .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR))
        );
    }

    @Override
    public List<SubjectOutputDto> getAllSubjects(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(subject -> mapper.subjectToSubjectOutputDto(subject))
                .toList();
    }

    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto) {
        Subject subject = mapper.subjectInputDtoToSubject(subjectInputDto);
        return mapper.subjectToSubjectOutputDto(subjectRepository.save(subject));
    }

    @Override
    public SubjectOutputDto updateSubjectById(SubjectInputDto subjectInputDto) {
        Subject currentSubject = subjectRepository
                .findById(subjectInputDto.getId_subject())
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR));

        currentSubject.setName(subjectInputDto.getName());

        currentSubject.setDescription(subjectInputDto.getDescription());

        return mapper.subjectToSubjectOutputDto(subjectRepository.save(currentSubject));
    }

    @Override
    public void deleteSubjectById(String id_subject) {
        subjectRepository.deleteById(id_subject);
    }
}
