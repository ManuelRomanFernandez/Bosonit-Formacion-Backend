package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto getPersonaById(Integer id) {
        return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")).personaToPersonaOutputDto();
    }

    @Override
    public Iterable<PersonaOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception {
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona) {
        personaRepository.findById(persona.getId_persona())
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));
        return personaRepository.save(new Persona(persona))
                .personaToPersonaOutputDto();
    }

    @Override
    public void deletePersonaById(Integer id) {
        personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));
        personaRepository.deleteById(id);

    }
}
