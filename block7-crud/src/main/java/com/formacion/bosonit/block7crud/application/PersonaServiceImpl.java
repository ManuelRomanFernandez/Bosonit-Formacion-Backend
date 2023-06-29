package com.formacion.bosonit.block7crud.application;

import com.formacion.bosonit.block7crud.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crud.controller.dto.PersonaOutputDto;
import com.formacion.bosonit.block7crud.domain.Persona;
import com.formacion.bosonit.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona) {
        personaRepository.findById(persona.getId()).orElseThrow();
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto deletePersona(int id) {
        PersonaOutputDto personaTmp = this.getPersonaById(id);
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
        return personaTmp;
    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow().personaToPersonaOutputDto();
    }

    @Override
    public Iterable<PersonaOutputDto> getPersonaByName(int pageNumber, int pageSize, String nombre) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getNombre().equals(nombre))
                .map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }
}
