package com.formacion.bosonit.block12mongodb.persona.application;

import com.formacion.bosonit.block12mongodb.errors.EntityNotFoundException;
import com.formacion.bosonit.block12mongodb.persona.controller.PersonaDTO;
import com.formacion.bosonit.block12mongodb.persona.domain.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonaServiceImpl implements PersonaService{
    final MongoTemplate mongoTemplate;

    public PersonaServiceImpl(@Qualifier("myConfig") MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PersonaDTO getPersonaById(Integer persona_id) {
        try{
            Query query = new Query().addCriteria(Criteria.where("_id").is(persona_id));
            return new PersonaDTO(Objects.requireNonNull(mongoTemplate.findOne(query, Persona.class)));
        } catch (Exception e){
            throw new EntityNotFoundException("No existe la entidad con el id indicado");
        }
    }

    @Override
    public List<PersonaDTO> getAllPersonas(Integer pageNumber, Integer pageSize) {
        List<Persona> list = mongoTemplate.findAll(Persona.class);
        List<PersonaDTO> output = new ArrayList<>();
        Integer count = getPersonaCount();
        pageNumber++;

        if (count < (pageNumber * pageSize)){
            for (int i = (pageNumber * pageSize - pageSize); i < (count); i++){
                output.add(new PersonaDTO(list.get(i)));
            }
        } else {
            for (int i = (pageNumber * pageSize - pageSize); i < (pageNumber * pageSize); i++){
                output.add(new PersonaDTO(list.get(i)));
            }
        }

        return output;
    }

    @Override
    public PersonaDTO addPersona(PersonaDTO personaDTO) {
        Persona newPersona = personaDTO.personaDtoToPersona(personaDTO);

        if(mongoTemplate.collectionExists(Persona.class)){
            Integer count = getPersonaCount();
            newPersona.setPersona_id(count + 1);
        } else {
            newPersona.setPersona_id(1);
        }

        return new PersonaDTO(mongoTemplate.insert(newPersona));
    }

    @Override
    public PersonaDTO updatePersonaById(Integer id, PersonaDTO personaDTO) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("_id", id);
        if (personaDTO.getUsuario() != null)
            update.set("usuario", personaDTO.getUsuario());
        if (personaDTO.getPassword() != null)
            update.set("password", personaDTO.getPassword());
        if (personaDTO.getName() != null)
            update.set("name", personaDTO.getName());
        if (personaDTO.getSurname() != null)
            update.set("surname", personaDTO.getSurname());
        if (personaDTO.getCompany_email() != null)
            update.set("company_email", personaDTO.getCompany_email());
        if (personaDTO.getPersonal_email() != null)
            update.set("personal_email", personaDTO.getPersonal_email());
        if (personaDTO.getCity() != null)
            update.set("city", personaDTO.getCity());
        if (personaDTO.getActive() != null)
            update.set("active", personaDTO.getActive());
        if (personaDTO.getCreated_date() != null)
            update.set("created_date", personaDTO.getCreated_date());
        if (personaDTO.getImagen_url() != null)
            update.set("imagen_url", personaDTO.getImagen_url());
        if (personaDTO.getTermination_date() != null)
            update.set("termination_date", personaDTO.getTermination_date());

        mongoTemplate.updateFirst(query, update, Persona.class);

        return getPersonaById(id);
    }

    @Override
    public void deletePersonaById(Integer persona_id) {
        getPersonaById(persona_id);

        Query query = new Query().addCriteria(Criteria.where("_id").is(persona_id));
        mongoTemplate.remove(query, Persona.class, "persona");
    }

    private Integer getPersonaCount(){
        Query query = new Query().addCriteria(Criteria.where("_id").gt(0));
        long count = mongoTemplate.count(query, Persona.class);

        return (int) count;
    }
}
