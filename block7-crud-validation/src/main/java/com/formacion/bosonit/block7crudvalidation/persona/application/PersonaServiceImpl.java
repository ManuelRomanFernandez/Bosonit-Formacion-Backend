package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.*;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.mapper.PersonaMapper;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.repository.TeacherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @PersistenceContext
    EntityManager entityManager;
    private static final String ID_PERSON_ERROR = "No existe la persona con el id indicado";

    @Override
    public PersonaSimpleOutputDto getPersonaById(Integer id) {
        return mapper.personaToPersonaOutDto(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR)));
    }

    @Override
    public Object getFullPersonaById(Integer id){
        Persona currentPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR));

        return currentPersona.getStudent() != null
                ? mapper.personaToPersonaStudentOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR)))
                : mapper.personaToPersonaTeacherOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR)));
    }

    @Override
    public List<PersonaSimpleOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(persona -> mapper.personaToPersonaOutDto(persona))
                .toList();
    }

    @Override
    public List<Object> getFullPersonasByUsuario(Integer pageNumber, Integer pageSize, String usuario) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(this::checkPersonaTypeOutputDto)
                .toList();
    }

    @Override
    public List<PersonaSimpleOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(persona -> mapper.personaToPersonaOutDto(persona))
                .toList();
    }

    @Override
    public List<Object> getAllFullPersonas(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(this::checkPersonaTypeOutputDto)
                .toList();
    }

    @Override
    public TeacherSimpleOutputDto getTemplateTeacher(String id_teacher) {
        String url = "http://localhost:8081/teacher/" + id_teacher;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TeacherSimpleOutputDto> response = restTemplate.exchange(url, HttpMethod.GET, null, TeacherSimpleOutputDto.class);

        return response.getBody();
    }

    @Override
    public List<PersonaSimpleOutputDto> getCustomQuery(HashMap<String, Object> options) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        int pageNumber = (int) options.get("pageNumber");
        int pageSize = (int) options.get("pageSize");

        List<Predicate> predicates = new ArrayList<>();

        String field = options.get("field").toString();

        if (options.get("field").equals("created_date")){
            Date value = convertToDateViaSqlTimestamp(LocalDateTime.parse(options.get("value").toString()));

            if(options.get("operator").toString().equals("less")){
                predicates.add(cb.lessThan(root.get(field), value));
            } else {
                predicates.add(cb.greaterThan(root.get(field), value));
            }

            if (options.get("orderBy") != null)
                query.orderBy(cb.asc(root.get(options.get("orderBy").toString())));
        } else {
            String value = options.get("value").toString();

            predicates.add(cb.like(root.get(field),"%" + value + "%"));
        }

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager
                .createQuery(query)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList()
                .stream()
                .map(persona -> mapper.personaToPersonaOutDto(persona))
                .toList();
    }

    @Override
    public PersonaSimpleOutputDto addPersona(PersonaInputDto persona) {
        Persona newPersona = personaRepository.save(new Persona(persona));
        return mapper.personaToPersonaOutDto(newPersona);
    }

    @Override
    public PersonaSimpleOutputDto updatePersona(PersonaInputDto persona, Integer id) {
        Persona currentPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR));

        if(persona.getUsuario() != null)
            currentPersona.setUsuario(persona.getUsuario());

        if(persona.getPassword() != null)
            currentPersona.setPassword(persona.getPassword());

        if(persona.getName() != null)
            currentPersona.setName(persona.getName());

        currentPersona.setSurname(persona.getSurname());

        if(persona.getCompany_email() != null)
            currentPersona.setCompany_email(persona.getCompany_email());

        if(persona.getPersonal_email() != null)
            currentPersona.setPersonal_email(persona.getPersonal_email());

        if(persona.getCity() != null)
            currentPersona.setCity(persona.getCity());

        if(persona.getActive() != null)
            currentPersona.setActive(persona.getActive());

        if(persona.getCreated_date() != null)
            currentPersona.setCreated_date(persona.getCreated_date());

        currentPersona.setImagen_url(persona.getImagen_url());

        currentPersona.setTermination_date(persona.getTermination_date());

        return mapper.personaToPersonaOutDto(personaRepository.save(currentPersona));
    }

    @Override
    public void deletePersonaById(Integer id) {
        Persona deletedPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ID_PERSON_ERROR));

        if (deletedPersona.getStudent() != null && deletedPersona.getStudent().getTeacher() != null){
            String id_teacher = deletedPersona.getStudent().getTeacher().getId_teacher();

            teacherRepository
                    .findById(id_teacher)
                    .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con el id indicado"))
                    .getStudents()
                    .remove(deletedPersona.getStudent());
        }
        else if (deletedPersona.getTeacher() != null){
            deletedPersona
                    .getTeacher()
                    .getStudents()
                    .forEach(e -> studentRepository
                            .findById(e.getId_student())
                            .orElseThrow(() -> new EntityNotFoundException("No existe el estudiante con el id indicado"))
                            .setTeacher(null));
        }

        personaRepository.deleteById(id);
    }

    private Object checkPersonaTypeOutputDto(Persona persona){
        if(persona.getStudent() != null){
            return mapper.personaToPersonaStudentOutPut(persona);
        } else if (persona.getTeacher() != null) {
            return mapper.personaToPersonaTeacherOutPut(persona);
        } else {
            return mapper.personaToPersonaOutDto(persona);
        }
    }

    private Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
