package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaTeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaStudentOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.respository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public PersonaOutputDto getPersonaById(Integer id) {
        return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")).personaToPersonaOutputDto();
    }

    @Override
    public PersonaStudentOutputDto getPersonaStudentById(Integer id){
        return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")).personaToPersonaStudentOutputDto();
    }

    @Override
    public PersonaTeacherOutputDto getPersonaTeacherById(Integer id){
        return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")).personaToPersonaTeacherOutputDto();
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
    public Iterable getFullPersonaStudentByUsuario(Integer pageNumber, Integer pageSize, String usuario) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .filter(persona -> persona.getUsuario().equals(usuario))
                .map(persona -> {
                    if(persona.getStudent() != null){
                        return persona.personaToPersonaStudentOutputDto();
                    } else if (persona.getTeacher() != null) {
                        return persona.personaToPersonaTeacherOutputDto();
                    } else {
                        return persona.personaToPersonaOutputDto();
                    }
                }).toList();
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToPersonaOutputDto).toList();
    }

    @Override
    public Iterable getAllFullPersonas(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(persona -> {
                    if(persona.getStudent() != null){
                        return persona.personaToPersonaStudentOutputDto();
                    } else if (persona.getTeacher() != null) {
                        return persona.personaToPersonaTeacherOutputDto();
                    } else {
                        return persona.personaToPersonaOutputDto();
                    }
                }).toList();
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception {
        return personaRepository.save(new Persona(persona)).personaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona, Integer id) {
        Persona currentPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

        currentPersona.setId_persona(id);

        currentPersona.setUsuario(persona.getUsuario());

        currentPersona.setPassword(persona.getPassword());

        currentPersona.setName(persona.getName());

        currentPersona.setSurname(persona.getSurname() != null
                ? persona.getSurname()
                : currentPersona.getSurname());

        currentPersona.setCompany_email(persona.getCompany_email());

        currentPersona.setCompany_email(persona.getCompany_email());

        currentPersona.setCity(persona.getCity());

        currentPersona.setActive(persona.getActive());

        currentPersona.setCreated_date(persona.getCreated_date());

        currentPersona.setImagen(persona.getImagen() != null
                ? persona.getImagen()
                : currentPersona.getImagen());

        currentPersona.setTermination_date(persona.getTermination_date() != null
                ? persona.getTermination_date()
                : currentPersona.getTermination_date());

        return personaRepository.save(currentPersona)
                .personaToPersonaOutputDto();
    }

    @Override
    public void deletePersonaById(Integer id) {
        Persona deletedPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

        if(deletedPersona.getStudent() != null){
            teacherRepository.findById(deletedPersona.getStudent().getTeacher().getId_teacher())
                    .orElseThrow().getStudents().remove(deletedPersona.getStudent());
        }

        if(deletedPersona.getTeacher() != null){
            deletedPersona.getTeacher().getStudents().forEach(e -> {
                studentRepository.findById(e.getId_student())
                        .orElseThrow()
                        .setTeacher(null);
            });
        }

        personaRepository.deleteById(id);
    }
}
