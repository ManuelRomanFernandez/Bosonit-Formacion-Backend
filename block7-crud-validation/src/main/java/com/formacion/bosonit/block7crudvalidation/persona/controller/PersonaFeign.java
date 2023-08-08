package com.formacion.bosonit.block7crudvalidation.persona.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8081/teacher", name = "crudValidationFeign")
public interface PersonaFeign {
    @GetMapping("/{idTeacher}")
    ResponseEntity<Object> getTeacherById(
            @PathVariable String idTeacher,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    );
}
