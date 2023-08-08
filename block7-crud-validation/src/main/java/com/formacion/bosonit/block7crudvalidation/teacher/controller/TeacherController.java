package com.formacion.bosonit.block7crudvalidation.teacher.controller;

import com.formacion.bosonit.block7crudvalidation.teacher.application.TeacherService;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/{id_teacher}")
    public ResponseEntity<Object> getTeacherById(
            @PathVariable String id_teacher,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    ){
        return outputType.equals("full")
                ? ResponseEntity.ok().body(teacherService.getFullTeacherById(id_teacher))
                : ResponseEntity.ok().body(teacherService.getSimpleTeacherById(id_teacher));
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeachers(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    ){
        return outputType.equals("full")
                ? ResponseEntity.ok().body(teacherService.getAllFullTeachers(pageNumber, pageSize))
                : ResponseEntity.ok().body(teacherService.getAllSimpleTeachers(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<TeacherFullOutputDto> addTeacher(@Valid @RequestBody TeacherInputDto teacherInputDto){
        URI location = URI.create("/create");
        return ResponseEntity.created(location).body(teacherService.addTeachers(teacherInputDto));
    }

    @PutMapping
    public ResponseEntity<TeacherSimpleOutputDto> updateTeacherById(@Valid @RequestBody TeacherInputDto teacherInputDto){
        teacherService.getSimpleTeacherById(teacherInputDto.getId_teacher());
        return ResponseEntity.ok().body(teacherService.updateTeacherById(teacherInputDto));
    }

    @DeleteMapping
    public void deleteTeacherById(@RequestParam String idTeacher){
        teacherService.deleteTeacherById(idTeacher);
    }

}
