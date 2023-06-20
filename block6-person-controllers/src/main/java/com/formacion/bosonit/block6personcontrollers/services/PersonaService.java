package com.formacion.bosonit.block6personcontrollers.services;

import com.formacion.bosonit.block6personcontrollers.models.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    public Persona personTmp = new Persona();
    public Persona createPersona(String nombre, String poblacion, int edad) {
        personTmp.setName(nombre);
        personTmp.setCity(poblacion);
        personTmp.setAge(edad);
        return personTmp;
    }
}
