package com.bosonit.formacion.block14springsecurity.application;

import com.bosonit.formacion.block14springsecurity.controller.dto.PersonaDto;
import com.bosonit.formacion.block14springsecurity.controller.mapper.PersonaMapper;
import com.bosonit.formacion.block14springsecurity.domain.Persona;
import com.bosonit.formacion.block14springsecurity.exception.EntityNotFoundException;
import com.bosonit.formacion.block14springsecurity.repository.PersonaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository repository;
    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);

    @Override
    public PersonaDto getPersonaById(int id_persona) {
        return mapper.personaToPersonaDto(repository.findById(id_persona)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public PersonaDto getPersonaByUsuario(String usuario) {
        return mapper.personaToPersonaDto(repository.findAll()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public List<PersonaDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return repository
                .findAll(pageRequest)
                .getContent()
                .stream()
                .map(persona -> mapper.personaToPersonaDto(persona))
                .toList();
    }

    @Override
    public PersonaDto addPersona(PersonaDto dto) {
        Persona newPersona = repository.save(mapper.personaDtoToPersona(dto));
        return mapper.personaToPersonaDto(newPersona);
    }

    @Override
    public PersonaDto updatePersonaById(int persona_id, PersonaDto dto) {
        Persona update = repository.findById(persona_id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

        if (dto.getUsuario() != null && usuarioIsAvailable(dto.getUsuario()))
            update.setUsuario(dto.getUsuario());
        if (dto.getName() != null)
            update.setName(dto.getName());
        if (dto.getSurname() != null)
            update.setSurname(dto.getSurname());
        if (dto.getCompany_email() != null)
            update.setCompany_email(dto.getCompany_email());
        if (dto.getPersonal_email() != null)
            update.setPersonal_email(dto.getPersonal_email());
        if (dto.getCity() != null)
            update.setCity(dto.getCity());
        if (dto.getActive() != null)
            update.setActive(dto.getActive());
        update.setImagen_url(dto.getImagen_url());
        update.setTermination_date(dto.getTermination_date());

        return mapper.personaToPersonaDto(repository.save(update));
    }

    @Override
    public void deletePersonaById(int id_persona) {
        repository.deleteById(id_persona);
    }

    private boolean usuarioIsAvailable(String usuario){
        return repository
                .findAll()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .toList()
                .size() == 0;
    }
}
