package com.formacion.bosonit.block7crud.controller;

import com.formacion.bosonit.block7crud.application.PersonaServiceImpl;
import com.formacion.bosonit.block7crud.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class ControllerPut {
    @Autowired
    PersonaServiceImpl personaService;

    @PutMapping("/{id}")
    public ResponseEntity updatePersona(@RequestBody PersonaInputDto personaInputDto, @PathVariable Integer id) {
        try {
            PersonaOutputDto personaTmp = personaService.getPersonaById(id);

            personaInputDto.setId(id);
            personaInputDto.setNombre(personaInputDto.getNombre() == null ? personaTmp.getNombre() : personaInputDto.getNombre());
            personaInputDto.setEdad(personaInputDto.getEdad() == null  ? personaTmp.getEdad() : personaInputDto.getEdad());
            personaInputDto.setPoblacion(personaInputDto.getPoblacion() == null  ? personaTmp.getPoblacion() : personaInputDto.getPoblacion());

            return  ResponseEntity.ok().body(personaService.updatePersona(personaInputDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
