package com.formacion.bosonit.block7crudvalidation.student_subject.controller;

import com.formacion.bosonit.block7crudvalidation.student_subject.application.StudentSubjectService;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/subject")
public class StudentSubjectController {
    @Autowired
    StudentSubjectService studentSubjectService;

    @GetMapping("/{id_student_subject}")
    public ResponseEntity<StudentSubjectOutputDto> getStudentSubjectById(@PathVariable String id_student_subject){
        return ResponseEntity.ok().body(studentSubjectService.getStudentSubjectById(id_student_subject));
    }

    @GetMapping("/student/{id_student}")
    public ResponseEntity<Iterable<StudentSubjectOutputDto>> getStudentSubjectsByStudentId(
            @PathVariable String id_student
    ){
        return ResponseEntity.ok().body(studentSubjectService.getStudentSubjectsByStudentId(id_student));
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
        URI location = URI.create("/subject");
        return ResponseEntity.created(location).body(studentSubjectService.addStudentSubject(studentSubjectInputDto));
    }

    @PutMapping
    public ResponseEntity<StudentSubjectOutputDto> updateStudentSubjectById(
            @Valid
            @RequestBody StudentSubjectInputDto studentSubjectInputDto
    ){
        studentSubjectService.getStudentSubjectById(studentSubjectInputDto.getId_student_subject());
        return ResponseEntity.ok().body(studentSubjectService.updateStudentSubjectById(studentSubjectInputDto));
    }

    @DeleteMapping
    public void deleteStudentSubjectById(@RequestParam String id_student_subject){
        studentSubjectService.deleteStudentSubjectById(id_student_subject);
    }

}
