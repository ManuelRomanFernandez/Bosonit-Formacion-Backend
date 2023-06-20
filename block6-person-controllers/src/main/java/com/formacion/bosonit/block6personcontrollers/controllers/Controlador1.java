package com.formacion.bosonit.block6personcontrollers.controllers;

import com.formacion.bosonit.block6personcontrollers.models.Ciudad;
import com.formacion.bosonit.block6personcontrollers.services.CiudadService;
import com.formacion.bosonit.block6personcontrollers.services.PersonaService;
import com.formacion.bosonit.block6personcontrollers.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    private PersonaService personaService;
    private CiudadService ciudadService;

    @Autowired
    public Controlador1(PersonaService personaService, CiudadService ciudadService) {
        this.personaService = personaService;
        this.ciudadService = ciudadService;
    }

    @GetMapping("/addPersona")
    public Persona addPersona(@RequestHeader("nombre") String nombre,
                              @RequestHeader("poblacion") String poblacion,
                              @RequestHeader("edad") int edad)
    {
        Persona persona = personaService.createPersona(nombre, poblacion, edad);
        return persona;
    }

    @PostMapping("/addCiudad")
    public ResponseEntity<String> addCiudad(@RequestParam String nombre, @RequestParam Integer habitantes){
        Ciudad ciudad = ciudadService.createCiudad(nombre, habitantes);
        ciudadService.addCiudad(ciudad);
        return new ResponseEntity<>(HttpStatus.OK + ": New city added ", HttpStatus.OK);
    }
}
