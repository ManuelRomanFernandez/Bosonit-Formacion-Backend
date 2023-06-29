package com.formacion.bosonit.block7crudvalidation.persona.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8081/teacher", name = "crudValidationFeign")
public interface PersonaFeign {
    @GetMapping("/{id_teacher}")
    ResponseEntity<?> getTeacherById(
            @PathVariable String id_teacher,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    );
}
