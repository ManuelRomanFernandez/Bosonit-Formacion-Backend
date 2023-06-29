package com.formacion.bosonit.block7crudvalidation.persona.application;

import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaTeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaStudentOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.mapper.PersonaMapper;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.respository.TeacherRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public PersonaOutputDto getPersonaById(Integer id) {
        return mapper.personaToPersonaOutDto(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public PersonaStudentOutputDto getPersonaStudentById(Integer id){
        return mapper.personaToPersonaStudentOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public PersonaTeacherOutputDto getPersonaTeacherById(Integer id){
        return mapper.personaToPersonaTeacherOutPut(personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado")));
    }

    @Override
    public Iterable<PersonaOutputDto> getPersonaByUsuario(Integer pageNumber, Integer pageSize, String usuario) {
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
                .map(persona -> {
                    if(persona.getStudent() != null){
                        return mapper.personaToPersonaStudentOutPut(persona);
                    } else if (persona.getTeacher() != null) {
                        return mapper.personaToPersonaTeacherOutPut(persona);
                    } else {
                        return mapper.personaToPersonaOutDto(persona);
                    }
                }).toList();
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(Integer pageNumber, Integer pageSize) {
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
                .map(persona -> {
                    if(persona.getStudent() != null){
                        return mapper.personaToPersonaStudentOutPut(persona);
                    } else if (persona.getTeacher() != null) {
                        return mapper.personaToPersonaTeacherOutPut(persona);
                    } else {
                        return mapper.personaToPersonaOutDto(persona);
                    }
                }).toList();
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        Persona newPersona = personaRepository.save(new Persona(persona));
        return mapper.personaToPersonaOutDto(newPersona);
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

        return mapper.personaToPersonaOutDto(personaRepository.save(currentPersona));
    }

    @Override
    public void deletePersonaById(Integer id) {
        Persona deletedPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id indicado"));

        if (deletedPersona.getStudent() != null){
            String id_teacher = deletedPersona.getStudent().getTeacher().getId_teacher();

            teacherRepository
                    .findById(id_teacher)
                    .orElseThrow()
                    .getStudents()
                    .remove(deletedPersona.getStudent());
        }
        else if (deletedPersona.getTeacher() != null){
            deletedPersona
                    .getTeacher()
                    .getStudents()
                    .forEach(e -> studentRepository
                            .findById(e.getId_student())
                            .orElseThrow()
                            .setTeacher(null));
        }

        personaRepository.deleteById(id);
    }
}
