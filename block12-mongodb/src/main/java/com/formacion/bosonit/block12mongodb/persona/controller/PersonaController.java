package com.formacion.bosonit.block12mongodb.persona.controller;

import com.formacion.bosonit.block12mongodb.persona.application.PersonaService;
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

    @GetMapping("/{persona_id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable Integer persona_id){
        return ResponseEntity.ok().body(personaService.getPersonaById(persona_id));
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ){
        return ResponseEntity.ok().body(personaService.getAllPersonas(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> addPersona(@RequestBody PersonaDTO personaDTO){
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(personaDTO));
    }

    @PutMapping("/{persona_id}")
    public ResponseEntity<PersonaDTO> updatePersonaById(
            @PathVariable Integer persona_id,
            @RequestBody PersonaDTO personaDTO){
        return ResponseEntity.ok().body(personaService.updatePersonaById(persona_id, personaDTO));
    }

    @DeleteMapping
    public void deletePersonaById(@RequestParam Integer persona_id){
        personaService.deletePersonaById(persona_id);
    }
}
