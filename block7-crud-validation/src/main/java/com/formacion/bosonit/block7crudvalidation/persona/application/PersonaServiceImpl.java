package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.*;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaStudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.mapper.PersonaMapper;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.repository.TeacherRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonaServiceImpl implements PersonaService {
    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public PersonaSimpleOutputDto getPersonaById(Integer id) {
        return mapper.personaToPersonaOutDto(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public Object getFullPersonaById(Integer id){
        Persona currentPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

        return currentPersona.getStudent() != null
                ? mapper.personaToPersonaStudentOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")))
                : mapper.personaToPersonaTeacherOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public PersonaStudentSimpleOutputDto getPersonaStudentById(Integer id){
        return mapper.personaToPersonaStudentOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public PersonaTeacherSimpleOutputDto getPersonaTeacherById(Integer id){
        return mapper.personaToPersonaTeacherOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public Iterable<PersonaSimpleOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(persona -> mapper.personaToPersonaOutDto(persona))
                .toList();
    }

    @Override
    public Iterable<?> getFullPersonaStudentByUsuario(Integer pageNumber, Integer pageSize, String usuario) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(this::checkPersonaTypeOutputDto)
                .toList();
    }

    @Override
    public Iterable<PersonaSimpleOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(persona -> mapper.personaToPersonaOutDto(persona))
                .toList();
    }

    @Override
    public Iterable<?> getAllFullPersonas(Integer pageNumber, Integer pageSize) {
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
    public PersonaSimpleOutputDto addPersona(PersonaInputDto persona) {
        Persona newPersona = personaRepository.save(new Persona(persona));
        return mapper.personaToPersonaOutDto(newPersona);
    }

    @Override
    public PersonaSimpleOutputDto updatePersona(PersonaInputDto persona, Integer id) {
        personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

        Persona currentPersona = new Persona(persona);

        currentPersona.setId_persona(id);

        currentPersona.setUsuario(persona.getUsuario() != null
                ? persona.getUsuario()
                : currentPersona.getUsuario());

        currentPersona.setPassword(persona.getPassword() != null
                ? persona.getPassword()
                : currentPersona.getPassword());

        currentPersona.setName(persona.getName() != null
                ? persona.getName()
                : currentPersona.getName());

        currentPersona.setSurname(persona.getSurname());

        currentPersona.setCompany_email(persona.getCompany_email() != null
                ? persona.getCompany_email()
                : currentPersona.getCompany_email());

        currentPersona.setCompany_email(persona.getCompany_email() != null
                ? persona.getCompany_email()
                : currentPersona.getCompany_email());

        currentPersona.setCity(persona.getCity() != null
                ? persona.getCity()
                : currentPersona.getCity());

        currentPersona.setActive(persona.getActive() != null
                ? persona.getActive()
                : currentPersona.getActive());

        currentPersona.setCreated_date(persona.getCreated_date() != null
                ? persona.getCreated_date()
                : currentPersona.getCreated_date());

        currentPersona.setImagen_url(persona.getImagen_url());

        currentPersona.setTermination_date(persona.getTermination_date());

        return mapper.personaToPersonaOutDto(personaRepository.save(currentPersona));
    }

    @Override
    public void deletePersonaById(Integer id) {
        Persona deletedPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

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
}
