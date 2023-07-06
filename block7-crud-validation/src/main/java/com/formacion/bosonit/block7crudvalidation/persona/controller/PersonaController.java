package com.formacion.bosonit.block7crudvalidation.persona.controller;

import com.formacion.bosonit.block7crudvalidation.persona.application.PersonaService;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaSimpleOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaFeign personaFeign;
    @Autowired
    PersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "simple", required = false) String outputType)
    {
        return outputType.equalsIgnoreCase("full")
                ? ResponseEntity.ok().body(personaService.getFullPersonaById(id))
                : ResponseEntity.ok().body(personaService.getPersonaById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getPersonaByName(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "simple", required = false) String outputType,
            @PathVariable String name)
    {
        return outputType.equalsIgnoreCase("full")
                ? ResponseEntity.ok().body(personaService.getFullPersonaStudentByUsuario(pageNumber, pageSize, name))
                : ResponseEntity.ok().body(personaService.getPersonaByUsuario(pageNumber, pageSize, name));

    }

    @GetMapping
    public ResponseEntity<?> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "simple", required = false) String outputType
    ){
        return outputType.equalsIgnoreCase("full")
                ? ResponseEntity.ok().body(personaService.getAllFullPersonas(pageNumber,pageSize))
                : ResponseEntity.ok().body(personaService.getAllPersonas(pageNumber,pageSize));

    }

    @GetMapping("/profesor/{id_teacher}")
    public ResponseEntity<?> getFeignTeacherById(
            @PathVariable String id_teacher,
            @RequestParam(defaultValue = "simple", required = false) String outputType,
            @RequestParam(defaultValue = "feign", required = false) String method
    ){
        return method.equalsIgnoreCase("template")
                ? ResponseEntity.ok().body(personaService.getTemplateTeacher(id_teacher))
                : personaFeign.getTeacherById(id_teacher, outputType);
    }

    @PostMapping
    public ResponseEntity<PersonaSimpleOutputDto> addPersona(@RequestBody PersonaInputDto persona) {
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaSimpleOutputDto> updateStudent(
            @RequestBody PersonaInputDto personaInputDto,
            @PathVariable Integer id)
    {
        personaService.getPersonaById(id);
        return ResponseEntity.ok().body(personaService.updatePersona(personaInputDto, id));
    }


    @DeleteMapping
    public void deletePersonaById(@RequestParam int id_persona) {
        personaService.deletePersonaById(id_persona);
    }

}
