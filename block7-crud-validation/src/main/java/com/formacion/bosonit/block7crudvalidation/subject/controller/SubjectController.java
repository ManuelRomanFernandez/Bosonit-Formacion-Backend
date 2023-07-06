package com.formacion.bosonit.block7crudvalidation.subject.controller;

import com.formacion.bosonit.block7crudvalidation.subject.application.SubjectService;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/{id_subject}")
    public ResponseEntity<SubjectOutputDto> getSubjectById(@PathVariable String id_subject){
        return ResponseEntity.ok().body(subjectService.getSubjectById(id_subject));
    }

    @GetMapping
    public ResponseEntity<Iterable<SubjectOutputDto>> getAllSubjects(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ){
        return ResponseEntity.ok().body(subjectService.getAllSubjects(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<SubjectOutputDto> addSubject(
            @Valid
            @RequestBody SubjectInputDto subjectInputDto
            ){
        URI location = URI.create("subject");
        return ResponseEntity.created(location).body(subjectService.addSubject(subjectInputDto));
    }

    @PutMapping
    public ResponseEntity<SubjectOutputDto> updateSubjectById(
            @Valid
            @RequestBody SubjectInputDto subjectInputDto
    ){
        subjectService.getSubjectById(subjectInputDto.getId_subject());
        return ResponseEntity.ok().body(subjectService.updateSubjectById(subjectInputDto));
    }

    @DeleteMapping
    public void deleteSubjectById(@RequestParam String id_subject){
        subjectService.deleteSubjectById(id_subject);
    }
}
