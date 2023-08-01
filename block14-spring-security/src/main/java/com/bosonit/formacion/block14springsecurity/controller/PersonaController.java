package com.bosonit.formacion.block14springsecurity.controller;

import com.bosonit.formacion.block14springsecurity.application.PersonaService;
import com.bosonit.formacion.block14springsecurity.controller.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @GetMapping("/{id_persona}")
    public ResponseEntity<PersonaDto> getPersonaById(@PathVariable int id_persona){
        return ResponseEntity.ok().body(personaService.getPersonaById(id_persona));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaDto> getPersonaByUsuario(@PathVariable String usuario){
        return ResponseEntity.ok().body(personaService.getPersonaByUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<PersonaDto>> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ){
        return ResponseEntity.ok().body(personaService.getAllPersonas(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<PersonaDto> addPersona(@RequestBody PersonaDto dto){
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(dto));
    }

    @PutMapping("/{id_persona}")
    public ResponseEntity<PersonaDto> updatePersonaById(
            @PathVariable int id_persona,
            @RequestBody PersonaDto dto){
        return ResponseEntity.ok().body(personaService.updatePersonaById(id_persona, dto));
    }

    @DeleteMapping
    public void deletePersonaById(@RequestParam int id_persona){
        personaService.deletePersonaById(id_persona);
    }

}
