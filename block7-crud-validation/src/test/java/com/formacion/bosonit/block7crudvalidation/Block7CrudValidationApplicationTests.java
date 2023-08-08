package com.formacion.bosonit.block7crudvalidation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.formacion.bosonit.block7crudvalidation.exception.UnprocessableEntityException;
import com.formacion.bosonit.block7crudvalidation.persona.application.PersonaService;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaInputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaStudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.controller.dto.PersonaTeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.persona.repository.PersonaRepository;
import com.formacion.bosonit.block7crudvalidation.student.application.StudentService;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.mapper.StudentMapper;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.student_subject.application.StudentSubjectService;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.mapper.StudentSubjectMapper;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import com.formacion.bosonit.block7crudvalidation.student_subject.repository.StudentSubjectRepository;
import com.formacion.bosonit.block7crudvalidation.subject.application.SubjectService;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.dto.SubjectOutputDto;
import com.formacion.bosonit.block7crudvalidation.subject.controller.mapper.SubjectMapper;
import com.formacion.bosonit.block7crudvalidation.subject.domain.Subject;
import com.formacion.bosonit.block7crudvalidation.subject.repository.SubjectRepository;
import com.formacion.bosonit.block7crudvalidation.teacher.application.TeacherService;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherFullOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.mapper.TeacherMapper;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.teacher.repository.TeacherRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Block7CrudValidationApplicationTests {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;
    @Autowired
    private StudentSubjectService studentSubjectService;
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
    StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);
    SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
    StudentSubjectMapper stuSubMapper = Mappers.getMapper(StudentSubjectMapper.class);

    private PersonaInputDto getPersonaInput() {
        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setUsuario("Persona1");
        personaInputDto.setPassword("Password1");
        personaInputDto.setName("Juan");
        personaInputDto.setSurname("Perez");
        personaInputDto.setCompany_email("email1@empresa.com");
        personaInputDto.setPersonal_email("email1@personal.com");
        personaInputDto.setCity("Barcelona");
        personaInputDto.setActive(true);
        personaInputDto.setCreated_date(new Date());
        personaInputDto.setImagen_url("imagen.jpg");
        personaInputDto.setTermination_date(new Date());

        return personaInputDto;
    }

    private StudentInputDto getStudentInput() {
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setNum_hours_week(10);
        studentInputDto.setComments("Comentarios");
        studentInputDto.setBranch("Maths");

        return studentInputDto;
    }

    private TeacherInputDto getTeacherInput() {
        TeacherInputDto teacherInputDto = new TeacherInputDto();
        teacherInputDto.setComments("Comentarios");
        teacherInputDto.setBranch("Maths");

        return teacherInputDto;
    }

    private SubjectInputDto getSubjectInput() {
        SubjectInputDto subjectInputDto = new SubjectInputDto();
        subjectInputDto.setName("Programming");
        subjectInputDto.setDescription("Java Spring Boot");

        return subjectInputDto;
    }

    private StudentSubjectInputDto getStudentSubjectInput() {
        StudentSubjectInputDto studentSubjectInputDto = new StudentSubjectInputDto();
        studentSubjectInputDto.setComments("Comentarios");
        studentSubjectInputDto.setInitial_date(new Date());
        studentSubjectInputDto.setFinish_date(new Date());

        return studentSubjectInputDto;
    }

    private Persona loadPersona() {
        PersonaInputDto personaInputDto = getPersonaInput();
        return new Persona(personaInputDto);
    }

    private Persona loadStudentPersona() {
        Persona persona = loadPersona();
        Teacher teacher = loadTeacher();

        StudentInputDto studentInputDto = getStudentInput();
        studentInputDto.setId_persona(persona.getId_persona());

        Student student = studentMapper.studentInputDtoToStudent(studentInputDto);

        teacher.getStudents().add(student);
        student.setTeacher(teacher);

        student.setStudentSubjects(Collections.emptySet());

        persona.setStudent(student);
        student.setPersona(persona);

        personaRepository.save(persona);
        teacherRepository.save(teacher);
        studentRepository.save(student);

        return persona;
    }

    private Persona loadTeacherPersona(){
        Persona persona = loadPersona();
        Student student = loadStudent();

        TeacherInputDto teacherInputDto = getTeacherInput();
        teacherInputDto.setId_persona(persona.getId_persona());

        Teacher teacher = teacherMapper.teacherInputDtoToTeacher(teacherInputDto);

        Set<Student> set = new HashSet<>();
        set.add(student);

        student.setTeacher(teacher);
        teacher.setStudents(set);

        persona.setTeacher(teacher);
        teacher.setPersona(persona);

        personaRepository.save(persona);
        studentRepository.save(student);
        teacherRepository.save(teacher);

        return persona;
    }

    private Student loadStudent() {
        Persona persona = loadPersona();

        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setId_persona(persona.getId_persona());

        Student student = studentMapper.studentInputDtoToStudent(studentInputDto);

        student.setStudentSubjects(new HashSet<StudentSubject>());

        persona.setStudent(student);
        student.setPersona(persona);

        personaRepository.save(persona);
        studentRepository.save(student);

        return student;
    }

    private Teacher loadTeacher(){
        Persona persona = loadPersona();

        TeacherInputDto teacherInputDto = getTeacherInput();
        teacherInputDto.setId_persona(persona.getId_persona());

        Teacher teacher = teacherMapper.teacherInputDtoToTeacher(teacherInputDto);
        teacher.setStudents(new HashSet<Student>());

        persona.setTeacher(teacher);
        teacher.setPersona(persona);

        personaRepository.save(persona);
        teacherRepository.save(teacher);

        return teacher;
    }

    private Subject loadSubject(){
        Subject subject = subjectMapper.subjectInputDtoToSubject(getSubjectInput());
        subject.setStudentSubjects(new HashSet<StudentSubject>());

        return subjectRepository.save(subject);
    }

    private StudentSubject loadStudentSubject(Student student, Subject subject){
        StudentSubject studentSubject = stuSubMapper
                .studentSubjectInputDtoToStudentSubject(getStudentSubjectInput());

        student.getStudentSubjects().add(studentSubject);
        studentSubject.setStudent(student);

        subject.getStudentSubjects().add(studentSubject);
        studentSubject.setSubject(subject);

        studentRepository.save(student);
        subjectRepository.save(subject);

        return studentSubjectRepository.save(studentSubject);
    }

    @BeforeEach
    private void clear() {
        teacherRepository.deleteAll();
        studentRepository.deleteAll();
        personaRepository.deleteAll();
        subjectRepository.deleteAll();
        studentSubjectRepository.deleteAll();
    }

    /**
     * Persona Tests
     */

    @Test
    void getPersonById() throws Exception {
        Persona loadPersona = personaRepository.save(loadPersona());

        MvcResult result = mockMvc.perform(get("/persona/" + loadPersona.getId_persona())).andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        Persona newPersona = objectMapper.readValue(jsonResponse, Persona.class);
        PersonaSimpleOutputDto currentPersona = personaService.getPersonaById(loadPersona.getId_persona());

        Assertions.assertEquals(loadPersona.getUsuario(), currentPersona.getUsuario());
        Assertions.assertEquals(currentPersona.getUsuario(), newPersona.getUsuario());
    }

    @Test
    void getFullStudentPersonaById() throws Exception {
        Persona loadPersona = loadStudentPersona();

        MvcResult result = mockMvc.perform(get("/persona/" + loadPersona.getId_persona())
                .param("outputType", "full"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        PersonaStudentSimpleOutputDto newPersona = objectMapper.readValue(jsonResponse, PersonaStudentSimpleOutputDto.class);
        PersonaStudentSimpleOutputDto currentPersona = (PersonaStudentSimpleOutputDto) personaService.getFullPersonaById(loadPersona.getId_persona());

        Assertions.assertEquals(loadPersona.getStudent().getId_student(), currentPersona.getId_student());
        Assertions.assertEquals(currentPersona.getId_student(), newPersona.getId_student());
    }

    @Test
    void getFullTeacherPersonaById() throws Exception {
        Persona loadPersona = loadTeacherPersona();

        MvcResult result = mockMvc.perform(get("/persona/" + loadPersona.getId_persona())
                .param("outputType", "full"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        PersonaTeacherSimpleOutputDto newPersona = objectMapper.readValue(jsonResponse, PersonaTeacherSimpleOutputDto.class);
        PersonaTeacherSimpleOutputDto currentPersona = (PersonaTeacherSimpleOutputDto) personaService.getFullPersonaById(loadPersona.getId_persona());

        Assertions.assertEquals(loadPersona.getTeacher().getId_teacher(), currentPersona.getId_teacher());
        Assertions.assertEquals(currentPersona.getId_teacher(), newPersona.getId_teacher());
    }

    @Test
    void getPersonasByUsuario() throws Exception {
        personaRepository.save(loadPersona());

        MvcResult result = mockMvc.perform(get("/persona/name/Persona1")
                        .param("outputType", "simple")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Persona.class);

        List<Persona> newPersonas = objectMapper.readValue(jsonResponse, collectionType);
        List<PersonaSimpleOutputDto> currentPersonas = personaService.getPersonaByUsuario(0,10, "Persona1");

        Assertions.assertEquals(newPersonas.size(), currentPersonas.size());
        Assertions.assertEquals(newPersonas.get(0).getName(), currentPersonas.get(0).getName());
    }

    @Test
    void getFullPersonasByUsuario() throws Exception {
        loadTeacherPersona();

        MvcResult result = mockMvc.perform(get("/persona/name/Persona1")
                        .param("outputType", "full")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class);

        List<?> newPersonas = objectMapper.readValue(jsonResponse, collectionType);
        List<?> currentPersonas = personaService.getFullPersonasByUsuario(0,10, "Persona1");

        Assertions.assertEquals(currentPersonas.size(), newPersonas.size());
    }

    @Test
    void getAllPersonas() throws Exception {
        personaRepository.save(loadPersona());

        MvcResult result = mockMvc.perform(get("/persona")
                    .param("outputType", "simple")
                    .param("pageNumber", String.valueOf(0))
                    .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Persona.class);

        List<Persona> newPersonas = objectMapper.readValue(jsonResponse, collectionType);
        List<PersonaSimpleOutputDto> currentPersonas = personaService.getAllPersonas(0,10);

        Assertions.assertEquals(currentPersonas.size(), newPersonas.size());
        Assertions.assertEquals(currentPersonas.get(0).getUsuario(), newPersonas.get(0).getUsuario());
    }

    @Test
    void getFullAllPersonas() throws Exception {
        loadTeacherPersona();

        MvcResult result = mockMvc.perform(get("/persona")
                        .param("outputType", "full")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class);

        List<?> newPersonas = objectMapper.readValue(jsonResponse, collectionType);
        List<?> currentPersonas = personaService.getFullPersonasByUsuario(0,4, "Persona1");

        Assertions.assertEquals(currentPersonas.size(), newPersonas.size());
    }

    @Test
    void getCustomQueryByName() throws Exception {
        personaRepository.save(loadPersona());

        MvcResult result = mockMvc.perform(get("/persona/criteriaQuery")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10))
                        .param("field", "name")
                        .param("value", "Juan")
                        .param("orderBy", "name"))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Persona.class);

        List<Persona> newPersonas = objectMapper.readValue(jsonResponse, collectionType);

        Assertions.assertEquals(1, newPersonas.size());
    }

    @Test
    void getCustomQueryBySurname() throws Exception {
        personaRepository.save(loadPersona());

        MvcResult result = mockMvc.perform(get("/persona/criteriaQuery")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10))
                        .param("field", "surname")
                        .param("value", "Perez")
                        .param("orderBy", "surname"))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Persona.class);

        List<Persona> newPersonas = objectMapper.readValue(jsonResponse, collectionType);

        Assertions.assertEquals(1, newPersonas.size());
    }

    @Test
    void getCustomQueryByDate() throws Exception {
        personaRepository.save(loadPersona());

        MvcResult result = mockMvc.perform(get("/persona/criteriaQuery")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10))
                        .param("field", "created_date")
                        .param("value", "1999-05-05T11:50:55")
                        .param("operator", "greater")
                        .param("orderBy", "surname"))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Persona.class);

        List<Persona> newPersonas = objectMapper.readValue(jsonResponse, collectionType);

        Assertions.assertEquals(1, newPersonas.size());
    }

    @Test
    void addPersona() throws Exception {
        PersonaInputDto personaInputDto = getPersonaInput();

        MvcResult result = mockMvc.perform(post("/persona")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaInputDto)))
                .andExpect(status().isCreated())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        PersonaSimpleOutputDto newPersona = objectMapper.readValue(jsonResponse, PersonaSimpleOutputDto.class);

        Assertions.assertEquals(personaInputDto.getUsuario(), newPersona.getUsuario());
    }


    @Test
    void updatePersonaById() throws Exception {
        Persona currentPersona = personaRepository.save(loadPersona());
        int id_persona = currentPersona.getId_persona();

        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setUsuario("UserNew");
        personaInputDto.setPassword("PasswordNew");
        personaInputDto.setName("Francisco");
        personaInputDto.setSurname("Garcia");
        personaInputDto.setCompany_email("newEmail@company.com");
        personaInputDto.setPersonal_email("newEmail@personal.com");
        personaInputDto.setCity("Madrid");
        personaInputDto.setActive(false);
        personaInputDto.setCreated_date(new Date(2000,1,1,12,12,12));
        personaInputDto.setImagen_url("newImagen.png");
        personaInputDto.setTermination_date(new Date(2000,1,1,12,12,12));

        MvcResult result = mockMvc.perform(put("/persona/" + id_persona)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaInputDto)))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        PersonaSimpleOutputDto updatedPersona = objectMapper.readValue(jsonResponse, PersonaSimpleOutputDto.class);

        Assertions.assertEquals(currentPersona.getId_persona(), updatedPersona.getId_persona());
        Assertions.assertNotEquals(currentPersona.getUsuario(), updatedPersona.getUsuario());
        Assertions.assertNotEquals(currentPersona.getPassword(), updatedPersona.getPassword());
        Assertions.assertNotEquals(currentPersona.getName(), updatedPersona.getName());
        Assertions.assertNotEquals(currentPersona.getSurname(), updatedPersona.getSurname());
        Assertions.assertNotEquals(currentPersona.getCompany_email(), updatedPersona.getCompany_email());
        Assertions.assertNotEquals(currentPersona.getPersonal_email(), updatedPersona.getPersonal_email());
        Assertions.assertNotEquals(currentPersona.getCity(), updatedPersona.getCity());
        Assertions.assertNotEquals(currentPersona.getActive(), updatedPersona.getActive());
        Assertions.assertNotEquals(currentPersona.getCreated_date(), updatedPersona.getCreated_date());
        Assertions.assertNotEquals(currentPersona.getImagen_url(), updatedPersona.getImagen_url());
        Assertions.assertNotEquals(currentPersona.getTermination_date(), updatedPersona.getTermination_date());
    }

    @Test
    void deletePersonaById() throws Exception {
        Persona currentPersona = personaRepository.save(loadPersona());
        int id_persona = currentPersona.getId_persona();

        mockMvc.perform(delete("/persona")
                .param("id_persona", String.valueOf(id_persona)))
        .andExpect(status().isOk())
        .andReturn();

        List<PersonaSimpleOutputDto> currentPersonas = personaService.getAllPersonas(0,10);

        Assertions.assertEquals(0, currentPersonas.size());
    }

    @Test
    void deletePersonaStudentById() throws Exception {
        Persona deletedPersona = loadStudentPersona();
        int id_personaTeacher = deletedPersona.getStudent().getTeacher().getPersona().getId_persona();
        int id_persona = deletedPersona.getId_persona();

        mockMvc.perform(delete("/persona")
                .param("id_persona", String.valueOf(id_persona)))
        .andExpect(status().isOk())
        .andReturn();

        List<PersonaSimpleOutputDto> currentPersonas = personaService.getAllPersonas(0,10);

        Assertions.assertEquals(1, currentPersonas.size());
        Assertions.assertEquals(id_personaTeacher, currentPersonas.get(0).getId_persona());
    }

    @Test
    void deletePersonaTeacherById() throws Exception {
        Persona deletedPersona = loadTeacherPersona();
        int id_personaStudent = deletedPersona.getTeacher().getStudents().iterator().next().getPersona().getId_persona();
        int id_persona = deletedPersona.getId_persona();

        mockMvc.perform(delete("/persona")
                        .param("id_persona", String.valueOf(id_persona)))
                .andExpect(status().isOk())
                .andReturn();

        List<PersonaSimpleOutputDto> currentPersonas = personaService.getAllPersonas(0,10);

        Assertions.assertEquals(1, currentPersonas.size());
        Assertions.assertEquals(id_personaStudent, currentPersonas.get(0).getId_persona());
    }

    /**
     * Student Tests
     */

    @Test
    void getFullStudentById() throws Exception {
        Student loadStudent = loadStudent();

        MvcResult result = mockMvc.perform(get("/student/" + loadStudent.getId_student())
                        .param("outputType", "full"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        StudentFullOutputDto newStudent = objectMapper.readValue(jsonResponse, StudentFullOutputDto.class);
        StudentFullOutputDto currentStudent = studentService.getFullStudentById(loadStudent.getId_student());

        Assertions.assertEquals(loadStudent.getId_student(), currentStudent.getId_student());
        Assertions.assertEquals(loadStudent.getPersona().getId_persona(), currentStudent.getId_persona());
        Assertions.assertEquals(currentStudent.getId_student(), newStudent.getId_student());
        Assertions.assertEquals(currentStudent.getId_persona(), newStudent.getId_persona());
    }

    @Test
    void getSimpleStudentById() throws Exception {
        Student loadStudent = loadStudent();

        MvcResult result = mockMvc.perform(get("/student/" + loadStudent.getId_student())
                        .param("outputType", "simple"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        StudentSimpleOutputDto newStudent = objectMapper.readValue(jsonResponse, StudentSimpleOutputDto.class);
        StudentSimpleOutputDto currentStudent = studentService.getSimpleStudentById(loadStudent.getId_student());

        Assertions.assertEquals(loadStudent.getId_student(), currentStudent.getId_student());
        Assertions.assertEquals(currentStudent.getId_student(), newStudent.getId_student());
    }

    @Test
    void getAllFullStudents() throws Exception {
        Student loadStudent = loadStudent();

        MvcResult result = mockMvc.perform(get("/student")
                        .param("outputType", "full")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, StudentFullOutputDto.class);

        List<StudentFullOutputDto> newStudents = objectMapper.readValue(jsonResponse, collectionType);
        List<StudentFullOutputDto> currentStudents = studentService.getAllFullStudents(0,4);

        Assertions.assertEquals(loadStudent.getId_student(), currentStudents.get(0).getId_student());
        Assertions.assertEquals(loadStudent.getPersona().getId_persona(), currentStudents.get(0).getId_persona());
        Assertions.assertEquals(currentStudents.size(), newStudents.size());
        Assertions.assertEquals(currentStudents.get(0).getId_student(), newStudents.get(0).getId_student());
        Assertions.assertEquals(currentStudents.get(0).getId_persona(), newStudents.get(0).getId_persona());
    }

    @Test
    void getAllSimpleStudents() throws Exception {
        Student loadStudent = loadStudent();

        MvcResult result = mockMvc.perform(get("/student")
                        .param("outputType", "simple")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, StudentSimpleOutputDto.class);

        List<StudentSimpleOutputDto> newStudents = objectMapper.readValue(jsonResponse, collectionType);
        List<StudentSimpleOutputDto> currentStudents = studentService.getAllSimpleStudents(0,10);

        Assertions.assertEquals(loadStudent.getId_student(), currentStudents.get(0).getId_student());
        Assertions.assertEquals(currentStudents.size(), newStudents.size());
        Assertions.assertEquals(currentStudents.get(0).getId_student(), newStudents.get(0).getId_student());
    }

    @Test
    void addStudent() throws Exception {
        Persona loadPersona = personaRepository.save(loadPersona());
        StudentInputDto studentInputDto = getStudentInput();
        studentInputDto.setId_persona(loadPersona.getId_persona());

        MvcResult result = mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentInputDto)))
                .andExpect(status().isCreated())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        StudentFullOutputDto newStudent = objectMapper.readValue(jsonResponse, StudentFullOutputDto.class);

        Assertions.assertEquals(loadPersona.getUsuario(), newStudent.getUsuario());
        Assertions.assertEquals(studentInputDto.getNum_hours_week(), newStudent.getNum_hours_week());
        Assertions.assertEquals(studentInputDto.getComments(), newStudent.getComments());
        Assertions.assertEquals(studentInputDto.getBranch(), newStudent.getBranch());
    }

    @Test
    void updateStudent() throws Exception {
        Student oldStudent = loadStudent();
        Persona newPersona = personaRepository.save(loadPersona());

        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setId_student(oldStudent.getId_student());
        studentInputDto.setId_persona(newPersona.getId_persona());
        studentInputDto.setNum_hours_week(999);
        studentInputDto.setComments("New comment");
        studentInputDto.setBranch("Music");

        MvcResult result = mockMvc.perform(put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentInputDto)))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        StudentSimpleOutputDto updatedStudent = objectMapper.readValue(jsonResponse, StudentSimpleOutputDto.class);

        Assertions.assertEquals(oldStudent.getId_student(), updatedStudent.getId_student());
        Assertions.assertNotEquals(oldStudent.getPersona().getId_persona(), updatedStudent.getId_persona());
        Assertions.assertNotEquals(oldStudent.getNum_hours_week(), updatedStudent.getNum_hours_week());
        Assertions.assertNotEquals(oldStudent.getComments(), updatedStudent.getComments());
        Assertions.assertNotEquals(oldStudent.getBranch(), updatedStudent.getBranch());
    }

    @Test
    void deleteStudent() throws Exception {
        Student currentStudent = loadStudent();
        String id_student = currentStudent.getId_student();

        mockMvc.perform(delete("/student")
                        .param("id_student", String.valueOf(id_student)))
                .andExpect(status().isOk())
                .andReturn();

        List<StudentSimpleOutputDto> currentStudents = studentService.getAllSimpleStudents(0,10);

        Assertions.assertEquals(0, currentStudents.size());
    }

    /**
     * Teacher Test
     */

    @Test
    void getFullTeacherById() throws Exception {
        Teacher loadTeacher = loadTeacher();

        MvcResult result = mockMvc.perform(get("/teacher/" + loadTeacher.getId_teacher())
                        .param("outputType", "full"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        TeacherFullOutputDto newTeacher = objectMapper.readValue(jsonResponse, TeacherFullOutputDto.class);
        TeacherFullOutputDto currentTeacher = teacherService.getFullTeacherById(loadTeacher.getId_teacher());

        Assertions.assertEquals(loadTeacher.getId_teacher(), currentTeacher.getId_teacher());
        Assertions.assertEquals(loadTeacher.getPersona().getId_persona(), currentTeacher.getId_persona());
        Assertions.assertEquals(currentTeacher.getId_teacher(), newTeacher.getId_teacher());
        Assertions.assertEquals(currentTeacher.getId_persona(), newTeacher.getId_persona());
    }

    @Test
    void getSimpleTeacherById() throws Exception {
        Teacher loadTeacher = loadTeacher();

        MvcResult result = mockMvc.perform(get("/teacher/" + loadTeacher.getId_teacher())
                        .param("outputType", "simple"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        TeacherSimpleOutputDto newTeacher = objectMapper.readValue(jsonResponse, TeacherSimpleOutputDto.class);
        TeacherSimpleOutputDto currentTeacher = teacherService.getSimpleTeacherById(loadTeacher.getId_teacher());

        Assertions.assertEquals(loadTeacher.getId_teacher(), currentTeacher.getId_teacher());
        Assertions.assertEquals(currentTeacher.getId_teacher(), newTeacher.getId_teacher());
    }

    @Test
    void getAllFullTeachers() throws Exception {
        Teacher loadTeacher = loadTeacher();

        MvcResult result = mockMvc.perform(get("/teacher")
                        .param("outputType", "full")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, TeacherFullOutputDto.class);

        List<TeacherFullOutputDto> newTeachers = objectMapper.readValue(jsonResponse, collectionType);
        List<TeacherFullOutputDto> currentTeachers = teacherService.getAllFullTeachers(0,10);

        Assertions.assertEquals(loadTeacher.getId_teacher(), currentTeachers.get(0).getId_teacher());
        Assertions.assertEquals(loadTeacher.getPersona().getId_persona(), currentTeachers.get(0).getId_persona());
        Assertions.assertEquals(currentTeachers.size(), newTeachers.size());
        Assertions.assertEquals(currentTeachers.get(0).getId_teacher(), newTeachers.get(0).getId_teacher());
        Assertions.assertEquals(currentTeachers.get(0).getId_persona(), newTeachers.get(0).getId_persona());
    }

    @Test
    void getAllSimpleTeachers() throws Exception {
        Teacher loadTeacher = loadTeacher();

        MvcResult result = mockMvc.perform(get("/teacher")
                        .param("outputType", "simple")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, TeacherSimpleOutputDto.class);

        List<TeacherSimpleOutputDto> newTeachers = objectMapper.readValue(jsonResponse, collectionType);
        List<TeacherSimpleOutputDto> currentTeachers = teacherService.getAllSimpleTeachers(0,10);

        Assertions.assertEquals(loadTeacher.getId_teacher(), currentTeachers.get(0).getId_teacher());
        Assertions.assertEquals(currentTeachers.size(), newTeachers.size());
        Assertions.assertEquals(currentTeachers.get(0).getId_teacher(), newTeachers.get(0).getId_teacher());
    }

    @Test
    void addTeacher() throws Exception {
        Persona loadPersona = personaRepository.save(loadPersona());
        TeacherInputDto teacherInputDto = getTeacherInput();
        teacherInputDto.setId_persona(loadPersona.getId_persona());

        MvcResult result = mockMvc.perform(post("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherInputDto)))
                .andExpect(status().isCreated())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        TeacherFullOutputDto newTeacher = objectMapper.readValue(jsonResponse, TeacherFullOutputDto.class);

        Assertions.assertEquals(loadPersona.getUsuario(), newTeacher.getUsuario());
        Assertions.assertEquals(teacherInputDto.getComments(), newTeacher.getComments());
        Assertions.assertEquals(teacherInputDto.getBranch(), newTeacher.getBranch());
    }

    @Test
    void updateTeacherById() throws Exception {
        Teacher oldTeacher = loadTeacher();
        Persona newPersona = personaRepository.save(loadPersona());

        TeacherInputDto teacherInputDto = new TeacherInputDto();
        teacherInputDto.setId_teacher(oldTeacher.getId_teacher());
        teacherInputDto.setId_persona(newPersona.getId_persona());
        teacherInputDto.setComments("New comment");
        teacherInputDto.setBranch("History");

        MvcResult result = mockMvc.perform(put("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherInputDto)))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        TeacherSimpleOutputDto updatedStudent = objectMapper.readValue(jsonResponse, TeacherSimpleOutputDto.class);

        Assertions.assertEquals(oldTeacher.getId_teacher(), updatedStudent.getId_teacher());
        Assertions.assertNotEquals(oldTeacher.getPersona().getId_persona(), updatedStudent.getId_persona());
        Assertions.assertNotEquals(oldTeacher.getComments(), updatedStudent.getComments());
        Assertions.assertNotEquals(oldTeacher.getBranch(), updatedStudent.getBranch());
    }

    @Test
    void deleteTeacherById() throws Exception {
        Teacher deletedTeacher = loadTeacher();
        String id_teacher = deletedTeacher.getId_teacher();

        mockMvc.perform(delete("/teacher")
                        .param("idTeacher", String.valueOf(id_teacher)))
                .andExpect(status().isOk())
                .andReturn();

        List<TeacherSimpleOutputDto> currentTeachers = teacherService.getAllSimpleTeachers(0,10);

        Assertions.assertEquals(0, currentTeachers.size());
    }

    /**
     * Subject Test
     */

    @Test
    void getSubjectById() throws Exception {
        Subject loadSubject = loadSubject();

        MvcResult result = mockMvc.perform(get("/subject/" + loadSubject.getId_subject()))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        SubjectOutputDto newSubject = objectMapper.readValue(jsonResponse, SubjectOutputDto.class);
        SubjectOutputDto currentSubject = subjectService.getSubjectById(loadSubject.getId_subject());

        Assertions.assertEquals(loadSubject.getId_subject(), currentSubject.getId_subject());
        Assertions.assertEquals(currentSubject.getId_subject(), newSubject.getId_subject());
    }

    @Test
    void getAllSubjects() throws Exception {
        Subject loadSubject = loadSubject();

        MvcResult result = mockMvc.perform(get("/subject")
                        .param("pageNumber", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, SubjectOutputDto.class);

        List<SubjectOutputDto> newSubjects = objectMapper.readValue(jsonResponse, collectionType);
        List<SubjectOutputDto> currentSubjects = subjectService.getAllSubjects(0,10);

        Assertions.assertEquals(loadSubject.getId_subject(), currentSubjects.get(0).getId_subject());
        Assertions.assertEquals(currentSubjects.size(), newSubjects.size());
        Assertions.assertEquals(currentSubjects.get(0).getId_subject(), newSubjects.get(0).getId_subject());
    }

    @Test
    void addSubject() throws Exception {
        SubjectInputDto subjectInputDto = getSubjectInput();

        MvcResult result = mockMvc.perform(post("/subject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subjectInputDto)))
                .andExpect(status().isCreated())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        SubjectOutputDto newSubject = objectMapper.readValue(jsonResponse, SubjectOutputDto.class);

        Assertions.assertEquals(subjectInputDto.getName(), newSubject.getName());
        Assertions.assertEquals(subjectInputDto.getDescription(), newSubject.getDescription());
    }

    @Test
    void updateSubject() throws Exception {
        Subject loadSubject = loadSubject();

        SubjectInputDto subjectInputDto = new SubjectInputDto();
        subjectInputDto.setId_subject(loadSubject.getId_subject());
        subjectInputDto.setName("Science");
        subjectInputDto.setDescription("Physics");

        MvcResult result = mockMvc.perform(put("/subject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subjectInputDto)))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        SubjectOutputDto updatedSubject = objectMapper.readValue(jsonResponse, SubjectOutputDto.class);

        Assertions.assertEquals(loadSubject.getId_subject(), updatedSubject.getId_subject());
        Assertions.assertNotEquals(loadSubject.getName(), updatedSubject.getName());
        Assertions.assertNotEquals(loadSubject.getDescription(), updatedSubject.getDescription());
    }

    @Test
    void deleteSubject() throws Exception {
        Subject deleteSubject = loadSubject();
        String id_subject = deleteSubject.getId_subject();

        mockMvc.perform(delete("/subject")
                        .param("id_subject", String.valueOf(id_subject)))
                .andExpect(status().isOk())
                .andReturn();

        List<SubjectOutputDto> currentSubjects = subjectService.getAllSubjects(0, 10);

        Assertions.assertEquals(0, currentSubjects.size());
    }

    /**
     * StudentSubject Tests
     */

    @Test
    void getStudentSubjectByIds() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        Subject loadSubject = loadSubject();
        String id_subject = loadSubject.getId_subject();

        loadStudentSubject(loadStudent, loadSubject);

        MvcResult result = mockMvc.perform(get("/studentSubject/" + id_student + "/" + id_subject))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        StudentSubjectOutputDto newStudentSubject = objectMapper.readValue(jsonResponse, StudentSubjectOutputDto.class);
        StudentSubjectOutputDto currentStudentSubject = studentSubjectService.getStudentSubjectByIds(id_student, id_subject);

        Assertions.assertEquals(id_student, currentStudentSubject.getId_student());
        Assertions.assertEquals(id_subject, currentStudentSubject.getId_subject());
        Assertions.assertEquals(currentStudentSubject.getId_student(), newStudentSubject.getId_student());
        Assertions.assertEquals(currentStudentSubject.getId_subject(), newStudentSubject.getId_subject());
    }

    @Test
    void getStudentSubjectsByStudentId() throws Exception{
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        Subject loadSubject = loadSubject();
        String id_subject = loadSubject.getId_subject();

        loadStudentSubject(loadStudent, loadSubject);

        MvcResult result = mockMvc.perform(get("/studentSubject/student/" + id_student))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, StudentSubjectOutputDto.class);

        List<StudentSubjectOutputDto> newStudentSubjects = objectMapper.readValue(jsonResponse, collectionType);
        List<StudentSubjectOutputDto> currentStudentSubjects = studentSubjectService.getStudentSubjectsByStudentId(id_student);

        Assertions.assertEquals(id_student, currentStudentSubjects.get(0).getId_student());
        Assertions.assertEquals(id_subject, currentStudentSubjects.get(0).getId_subject());
        Assertions.assertEquals(currentStudentSubjects.get(0).getId_student(), newStudentSubjects.get(0).getId_student());
        Assertions.assertEquals(currentStudentSubjects.get(0).getId_subject(), newStudentSubjects.get(0).getId_subject());
    }

    @Test
    void getStudentSubjectsBySubjectId() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        Subject loadSubject = loadSubject();
        String id_subject = loadSubject.getId_subject();

        loadStudentSubject(loadStudent, loadSubject);

        MvcResult result = mockMvc.perform(get("/studentSubject/subject/" + id_subject))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, StudentSubjectOutputDto.class);

        List<StudentSubjectOutputDto> newStudentSubjects = objectMapper.readValue(jsonResponse, collectionType);
        List<StudentSubjectOutputDto> currentStudentSubjects = studentSubjectService.getStudentSubjectsBySubjectId(id_subject);

        Assertions.assertEquals(id_student, currentStudentSubjects.get(0).getId_student());
        Assertions.assertEquals(id_subject, currentStudentSubjects.get(0).getId_subject());
        Assertions.assertEquals(currentStudentSubjects.get(0).getId_student(), newStudentSubjects.get(0).getId_student());
        Assertions.assertEquals(currentStudentSubjects.get(0).getId_subject(), newStudentSubjects.get(0).getId_subject());
    }

    @Test
    void getAllStudentSubjects() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        Subject loadSubject = loadSubject();
        String id_subject = loadSubject.getId_subject();

        loadStudentSubject(loadStudent, loadSubject);

        MvcResult result = mockMvc.perform(get("/studentSubject"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, StudentSubjectOutputDto.class);

        List<StudentSubjectOutputDto> newStudentSubjects = objectMapper.readValue(jsonResponse, collectionType);
        List<StudentSubjectOutputDto> currentStudentSubjects = studentSubjectService.getStudentSubjectsBySubjectId(id_subject);

        Assertions.assertEquals(id_student, currentStudentSubjects.get(0).getId_student());
        Assertions.assertEquals(id_subject, currentStudentSubjects.get(0).getId_subject());
        Assertions.assertEquals(currentStudentSubjects.get(0).getId_student(), newStudentSubjects.get(0).getId_student());
        Assertions.assertEquals(currentStudentSubjects.get(0).getId_subject(), newStudentSubjects.get(0).getId_subject());
    }

    @Test
    void addStudentSubject() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        Subject loadSubject = loadSubject();
        String id_subject = loadSubject.getId_subject();

        StudentSubjectInputDto inputDto = getStudentSubjectInput();
        inputDto.setId_student(id_student);
        inputDto.setId_subject(id_subject);

        MvcResult result = mockMvc.perform(post("/studentSubject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        StudentSubjectOutputDto newStudentSubject = objectMapper.readValue(jsonResponse, StudentSubjectOutputDto.class);

        Assertions.assertEquals(id_student, newStudentSubject.getId_student());
        Assertions.assertEquals(id_subject, newStudentSubject.getId_subject());
    }

    @Test
    void addMultipleStudentSubjectsByStudentId() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            Subject loadSubject = loadSubject();
            String id_subject = loadSubject.getId_subject();

            list.add(id_subject);
        }

        String subjects = list.toString().replaceAll("\\[|\\]", "").replaceAll("\\]","");

        mockMvc.perform(post("/studentSubject/" + id_student)
                        .param("subjects", subjects))
                .andExpect(status().isOk())
                .andReturn();

        List<StudentSubjectOutputDto> newStudentSubjects = studentSubjectService.getAllStudentSubjects(0,10);

        Assertions.assertEquals(3, newStudentSubjects.size());
        Assertions.assertEquals(id_student, newStudentSubjects.get(0).getId_student());
        Assertions.assertEquals(list.get(0), newStudentSubjects.get(0).getId_subject());
        Assertions.assertEquals(list.get(1), newStudentSubjects.get(1).getId_subject());
        Assertions.assertEquals(list.get(2), newStudentSubjects.get(2).getId_subject());
    }

    @Test
    void updateStudentSubjectById() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        Subject loadSubject = loadSubject();
        String id_subject = loadSubject.getId_subject();

        StudentSubject current = loadStudentSubject(loadStudent, loadSubject);

        StudentSubjectInputDto inputDto = new StudentSubjectInputDto();
        inputDto.setId_student(id_student);
        inputDto.setId_subject(id_subject);
        inputDto.setComments("New comments");
        inputDto.setInitial_date(new Date(2000, 1,1,12,12,12));
        inputDto.setFinish_date(new Date(2000, 1,1,12,12,12));

        MvcResult result = mockMvc.perform(put("/studentSubject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        StudentSubjectOutputDto updatedStudentSubject = objectMapper.readValue(jsonResponse, StudentSubjectOutputDto.class);

        Assertions.assertEquals(id_student, updatedStudentSubject.getId_student());
        Assertions.assertEquals(id_subject, updatedStudentSubject.getId_subject());
        Assertions.assertNotEquals(current.getComments(), updatedStudentSubject.getComments());
    }

    @Test
    void deleteStudentSubjectById() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        Subject loadSubject = loadSubject();
        String id_subject = loadSubject.getId_subject();

        loadStudentSubject(loadStudent, loadSubject);

        mockMvc.perform(delete("/studentSubject")
                        .param("id_student", id_student)
                        .param("id_subject", id_subject))
                .andExpect(status().isOk())
                .andReturn();

        List<StudentSubjectOutputDto> current = studentSubjectService.getAllStudentSubjects(0,10);

        Assertions.assertEquals(0, current.size());
    }

    @Test
    void deleteStudentSubjectsByStudentId() throws Exception {
        Student loadStudent = loadStudent();
        String id_student = loadStudent.getId_student();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            Subject loadSubject = loadSubject();
            String id_subject = loadSubject.getId_subject();

            list.add(id_subject);
        }

        String subjects = list.toString().replaceAll("\\[|\\]", "").replaceAll("\\]","");

        mockMvc.perform(post("/studentSubject/" + id_student)
                        .param("subjects", subjects))
                .andExpect(status().isOk())
                .andReturn();

        List<String> newList = new ArrayList<>();
        newList.add(list.get(0));
        newList.add(list.get(1));

        List<StudentSubjectOutputDto> old = studentSubjectService.getAllStudentSubjects(0,10);

        String delete = newList.toString().replaceAll("\\[|\\]", "").replaceAll("\\]","");

        mockMvc.perform(delete("/studentSubject/" + id_student)
                        .param("subjects", delete))
                .andExpect(status().isOk())
                .andReturn();

        List<StudentSubjectOutputDto> current = studentSubjectService.getAllStudentSubjects(0,10);

        Assertions.assertNotEquals(old.size(), current.size());
        Assertions.assertEquals(1, current.size());
        Assertions.assertEquals(old.get(2).getId_subject(), current.get(0).getId_subject());
    }

    /**
     * Persona Exceptions Tests
     */

    @ParameterizedTest
    @CsvSource({
            ", Usuario no puede ser nulo",
            "Uno, Longitud de usuario no puede ser inferior a 5 caracteres",
            "Uuuuuuuuuuuuuuuuuuuuser, Longitud de usuario no puede ser superior a 10 caracteres"
    })
    void validatePersonaInputDto(String usuario, String expectedMessage) {
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setUsuario(usuario);

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(expectedMessage));
    }

    @Test
    void passwordNullException(){
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setPassword(null);
        String MESSAGE = "Password no puede ser nulo";

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(MESSAGE));
    }

    @Test
    void NameNullException(){
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setName(null);
        String MESSAGE = "Name no puede ser nulo";

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(MESSAGE));
    }

    @Test
    void CompanyEmailNullException(){
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setCompany_email(null);
        String MESSAGE = "Company_Email no puede ser nulo";

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(MESSAGE));
    }

    @Test
    void PersonalEmailNullException(){
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setPersonal_email(null);
        String MESSAGE = "Personal_Email no puede ser nulo";

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(MESSAGE));
    }

    @Test
    void CityNullException(){
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setCity(null);
        String MESSAGE = "City no puede ser nulo";

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(MESSAGE));
    }

    @Test
    void ActiveNullException(){
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setActive(null);
        String MESSAGE = "Active no puede ser nulo";

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(MESSAGE));
    }

    @Test
    void CreatedDateNullException(){
        PersonaInputDto inputDto = getPersonaInput();
        inputDto.setCreated_date(null);
        String MESSAGE = "Created_date no puede ser nulo";

        UnprocessableEntityException thrown = Assertions.assertThrows(
                UnprocessableEntityException.class,
                () -> new Persona(inputDto)
        );

        Assertions.assertTrue(thrown.getMessage().contains(MESSAGE));
    }
}
