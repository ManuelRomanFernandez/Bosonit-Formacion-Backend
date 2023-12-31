package com.formacion.bosonit.block7crud.controller;

import com.formacion.bosonit.block7crud.application.PersonaServiceImpl;
import com.formacion.bosonit.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class ContollerGet {
    @Autowired
    PersonaServiceImpl personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getPersonaByName(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @PathVariable String name)
    {
        return ResponseEntity.ok().body(personaService.getPersonaByName(pageNumber, pageSize, name));
    }

    @GetMapping
    public ResponseEntity getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ){
        return ResponseEntity.ok().body(personaService.getAllPersonas(pageNumber,pageSize));
    }
}
