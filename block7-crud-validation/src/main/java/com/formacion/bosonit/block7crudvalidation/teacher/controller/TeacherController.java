package com.formacion.bosonit.block7crudvalidation.teacher.controller;

import com.formacion.bosonit.block7crudvalidation.teacher.application.TeacherServiceImpl;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;

    @GetMapping("/{id_teacher}")
    public ResponseEntity getTeacherById(
            @PathVariable String id_teacher,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    ){
        try{
            return outputType.equals("full")
                    ? ResponseEntity.ok().body(teacherService.getFullTeacherById(id_teacher))
                    : ResponseEntity.ok().body(teacherService.getSimpleTeacherById(id_teacher));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable> getAllTeachers(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    ){
        return outputType.equals("full")
                ? ResponseEntity.ok().body(teacherService.getAllFullTeachers(pageNumber, pageSize))
                : ResponseEntity.ok().body(teacherService.getAllSimpleTeachers(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<TeacherOutputDto> addTeacher(@RequestBody TeacherInputDto teacherInputDto){
        URI location = URI.create("/create");
        return ResponseEntity.created(location).body(teacherService.addTeachers(teacherInputDto));
    }

    @PutMapping
    public ResponseEntity<TeacherSimpleOutputDto> updateTeacherById(@RequestBody TeacherInputDto teacherInputDto){
        teacherService.getSimpleTeacherById(teacherInputDto.getId_teacher());
        return ResponseEntity.ok().body(teacherService.updateTeacherById(teacherInputDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTeacherById(@RequestParam String id_teacher){
        teacherService.deleteTeacherById(id_teacher);
        return ResponseEntity.ok().body("El profesor con id " + id_teacher + " ha sido eliminado");
    }

}
