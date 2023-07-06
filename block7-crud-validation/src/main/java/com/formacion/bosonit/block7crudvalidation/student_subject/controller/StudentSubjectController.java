package com.formacion.bosonit.block7crudvalidation.student_subject.controller;

import com.formacion.bosonit.block7crudvalidation.student_subject.application.StudentSubjectService;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/studentSubject")
public class StudentSubjectController {
    @Autowired
    StudentSubjectService studentSubjectService;

    @GetMapping("/{id_student}/{id_subject}")
    public ResponseEntity<StudentSubjectOutputDto> getStudentSubjectById(
            @PathVariable String id_student,
            @PathVariable String id_subject
    ){
        return ResponseEntity.ok().body(studentSubjectService.getStudentSubjectByIds(id_student, id_subject));
    }

    @GetMapping("/student/{id_student}")
    public ResponseEntity<Iterable<StudentSubjectOutputDto>> getStudentSubjectsByStudentId(
            @PathVariable String id_student
    ){
        return ResponseEntity.ok().body(studentSubjectService.getStudentSubjectsByStudentId(id_student));
    }

    @GetMapping("/subject/{id_subject}")
    public ResponseEntity<Iterable<StudentSubjectOutputDto>> getStudentSubjectsBySubjectId(
            @PathVariable String id_subject
    ){
        return ResponseEntity.ok().body(studentSubjectService.getStudentSubjectsBySubjectId(id_subject));
    }

    @GetMapping
    public ResponseEntity<Iterable<StudentSubjectOutputDto>> getAllStudentSubjects(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ){
        return ResponseEntity.ok().body(studentSubjectService.getAllStudentSubjects(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<StudentSubjectOutputDto> addStudentSubject(
            @Valid
            @RequestBody StudentSubjectInputDto studentSubjectInputDto
    ){
        URI location = URI.create("/studentSubject");
        return ResponseEntity.created(location).body(studentSubjectService.addStudentSubject(studentSubjectInputDto));
    }

    @PostMapping("/{id_student}")
    public void addMultipleStudentSubjectsByStudentId(
            @Valid
            @PathVariable String id_student,
            @RequestParam List<String> subjects
    ){
        studentSubjectService.addMultipleStudentSubjectsByStudentId(id_student,subjects);
    }

    @PutMapping
    public ResponseEntity<StudentSubjectOutputDto> updateStudentSubjectById(
            @Valid
            @RequestBody StudentSubjectInputDto studentSubjectInputDto
    ){
        return ResponseEntity.ok().body(studentSubjectService.updateStudentSubjectById(studentSubjectInputDto));
    }

    @DeleteMapping
    public void deleteStudentSubjectById(
            @RequestParam String id_student,
            @RequestParam String id_subject
    ){
        studentSubjectService.deleteStudentSubjectById(id_student, id_subject);
    }

    @DeleteMapping("/{id_student}")
    public void deleteStudentSubjectsByStudentId(
            @PathVariable String id_student,
            @RequestParam List<String> subjects
    ){
        studentSubjectService.deleteStudentSubjectsByStudentId(id_student, subjects);
    }
}
