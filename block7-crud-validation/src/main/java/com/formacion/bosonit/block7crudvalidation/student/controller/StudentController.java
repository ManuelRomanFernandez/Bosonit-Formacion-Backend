package com.formacion.bosonit.block7crudvalidation.student.controller;

import com.formacion.bosonit.block7crudvalidation.student.application.StudentServiceImpl;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/{id_student}")
    public ResponseEntity getStudentById(
            @Valid
            @PathVariable String id_student,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    ){
        try {
            return outputType.equals("full")
                    ? ResponseEntity.ok().body(studentService.getFullStudentById(id_student))
                    : ResponseEntity.ok().body(studentService.getSimpleStudentById(id_student));

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable> getAllStudents(
            @Valid
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    ){
        return outputType.equals("full")
                ? ResponseEntity.ok().body(studentService.getAllFullStudents(pageNumber, pageSize))
                : ResponseEntity.ok().body(studentService.getAllSimpleStudents(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@Valid @RequestBody StudentInputDto studentInputDto){
        URI location = URI.create("/student");
        return ResponseEntity.created(location).body(studentService.addStudent(studentInputDto));
    }

    @PutMapping
    public ResponseEntity<StudentSimpleOutputDto> updateStudentById(@Valid @RequestBody StudentInputDto studentInputDto){
        studentService.getSimpleStudentById(studentInputDto.getId_student());
        return ResponseEntity.ok().body(studentService.updateStudentById(studentInputDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@Valid @RequestParam String id_student) {
        studentService.deleteStudentById(id_student);
        return ResponseEntity.ok().body("El estudiante con id " + id_student + " ha sido eliminado");
    }

}
