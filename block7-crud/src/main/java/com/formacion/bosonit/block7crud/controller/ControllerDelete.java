package com.formacion.bosonit.block7crud.controller;

import com.formacion.bosonit.block7crud.application.PersonaServiceImpl;
import com.formacion.bosonit.block7crud.controller.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/persona")
public class ControllerDelete {

    @Autowired
    PersonaServiceImpl personaService;

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> deletePersona(@PathVariable Integer id){
        try{
            return ResponseEntity.status(202).body(personaService.deletePersona(id));
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
