package com.formacion.bosonit.block6personcontrollers.controllers;

import com.formacion.bosonit.block6personcontrollers.models.Ciudad;
import com.formacion.bosonit.block6personcontrollers.services.CiudadService;
import com.formacion.bosonit.block6personcontrollers.services.PersonaService;
import com.formacion.bosonit.block6personcontrollers.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    @Autowired
    private PersonaService personaService;
    @Autowired
    private CiudadService ciudadService;

    public Controlador2(PersonaService personaService, CiudadService ciudadService) {
        this.personaService = personaService;
        this.ciudadService = ciudadService;
    }

    @GetMapping("/getPersona")
    public Persona getPersona() {
        personaService.personTmp.multiplyEdadByTwo();
        return personaService.personTmp;
    }

    @GetMapping("/getCiudades")
    public List<Ciudad> getCiudades() {
        return ciudadService.cityList;
    }

}
