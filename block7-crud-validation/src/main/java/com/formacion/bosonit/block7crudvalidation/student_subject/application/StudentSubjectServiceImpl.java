package com.formacion.bosonit.block7crudvalidation.student_subject.application;

import com.formacion.bosonit.block7crudvalidation.exception.EntityNotFoundException;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.student.repository.StudentRepository;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.mapper.StudentSubjectMapper;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubjectCompositeKey;
import com.formacion.bosonit.block7crudvalidation.student_subject.repository.StudentSubjectRepository;
import com.formacion.bosonit.block7crudvalidation.subject.domain.Subject;
import com.formacion.bosonit.block7crudvalidation.subject.repository.SubjectRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {
    StudentSubjectMapper mapper = Mappers.getMapper(StudentSubjectMapper.class);
    @Autowired
    StudentSubjectRepository studentSubjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    private static final String ID_SUBJECT_STUDENT_ERROR = "No existe el estudiante_asignatura con los ids proporcionados";
    private static final String ID_STUDENT_ERROR = "No existe el estudiante con el id indicado";
    private static final String ID_SUBJECT_ERROR = "No existe la asignatura con el id indicado";

    @Override
    public StudentSubjectOutputDto getStudentSubjectByIds(String id_student, String id_subject) {
        Student student = studentRepository
                .findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR));

        Subject subject = subjectRepository
                .findById(id_subject)
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR));

        StudentSubjectCompositeKey key = new StudentSubjectCompositeKey(student.getId_student(), subject.getId_subject());

        return mapper.studentSubjectToStudentSubjectOutputDto(
                studentSubjectRepository
                        .findById(key)
                        .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_STUDENT_ERROR)));
    }

    @Override
    public List<StudentSubjectOutputDto> getStudentSubjectsByStudentId(String id_student) {
        return studentSubjectRepository.findAll()
                .stream()
                .filter(e -> e.getStudent().getId_student().equals(id_student))
                .map(studentSubject -> mapper.studentSubjectToStudentSubjectOutputDto(studentSubject))
                .toList();
    }

    @Override
    public List<StudentSubjectOutputDto> getStudentSubjectsBySubjectId(String id_subject) {
        return studentSubjectRepository.findAll()
                .stream()
                .filter(e -> e.getSubject().getId_subject().equals(id_subject))
                .map(studentSubject -> mapper.studentSubjectToStudentSubjectOutputDto(studentSubject))
                .toList();
    }

    @Override
    public List<StudentSubjectOutputDto> getAllStudentSubjects(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentSubjectRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(studentSubject -> mapper.studentSubjectToStudentSubjectOutputDto(studentSubject))
                .toList();
    }

    @Override
    public StudentSubjectOutputDto addStudentSubject(StudentSubjectInputDto studentSubjectInputDto) {
        StudentSubject studentSubject = mapper.studentSubjectInputDtoToStudentSubject(studentSubjectInputDto);

        Student student = studentRepository.findById(studentSubjectInputDto.getId_student())
                .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR));

        Subject subject = subjectRepository.findById(studentSubjectInputDto.getId_subject())
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR));

        student.getStudentSubjects().add(studentSubject);
        studentSubject.setStudent(student);

        subject.getStudentSubjects().add(studentSubject);
        studentSubject.setSubject(subject);

        studentRepository.save(student);
        subjectRepository.save(subject);

        return mapper.studentSubjectToStudentSubjectOutputDto(studentSubjectRepository.save(studentSubject));
    }

    @Override
    public void addMultipleStudentSubjectsByStudentId(String id_student, List<String> subjects) {
        subjects.forEach(subject -> {
            StudentSubjectInputDto input = new StudentSubjectInputDto();

            input.setId_student(id_student);
            input.setId_subject(subject);
            input.setInitial_date(new Date());

            this.addStudentSubject(input);
        });
    }

    @Override
    public StudentSubjectOutputDto updateStudentSubjectById(StudentSubjectInputDto studentSubjectInputDto) {
        Student inputStudent = studentRepository
                .findById(studentSubjectInputDto.getId_student())
                .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR));

        Subject inputSubject = subjectRepository
                .findById(studentSubjectInputDto.getId_subject())
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR));

        StudentSubjectCompositeKey key = new StudentSubjectCompositeKey(inputStudent.getId_student(), inputSubject.getId_subject());

        StudentSubject currentStudentSubject = studentSubjectRepository
                .findById(key)
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_STUDENT_ERROR));

        currentStudentSubject.setComments(studentSubjectInputDto.getComments());

        currentStudentSubject.setInitial_date(studentSubjectInputDto.getInitial_date());

        currentStudentSubject.setFinish_date(studentSubjectInputDto.getFinish_date());

        if(studentSubjectInputDto.getId_subject() != null){
            if(!Objects.equals(
                    currentStudentSubject.getSubject().getId_subject(),
                    studentSubjectInputDto.getId_subject()
            )){
                Subject oldSubject = subjectRepository
                        .findById(currentStudentSubject.getSubject().getId_subject())
                        .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR));

                oldSubject.getStudentSubjects().remove(currentStudentSubject);
            }

            inputSubject.getStudentSubjects().add(currentStudentSubject);
            currentStudentSubject.setSubject(inputSubject);

            subjectRepository.save(inputSubject);
        }

        if(studentSubjectInputDto.getId_student() != null){
            if(!Objects.equals(
                    currentStudentSubject.getStudent().getId_student(),
                    studentSubjectInputDto.getId_student())
            ){
                Student oldStudent = studentRepository
                        .findById(currentStudentSubject.getStudent().getId_student())
                        .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR));

                oldStudent.getStudentSubjects().remove(currentStudentSubject);
            }

            inputStudent.getStudentSubjects().add(currentStudentSubject);
            currentStudentSubject.setStudent(inputStudent);

            studentRepository.save(inputStudent);
        }

        return mapper.studentSubjectToStudentSubjectOutputDto(studentSubjectRepository.save(currentStudentSubject));
    }

    @Override
    public void deleteStudentSubjectById(String id_student, String id_subject) {
        Student student = studentRepository
                .findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR));

        Subject subject = subjectRepository
                .findById(id_subject)
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR));

        StudentSubjectCompositeKey key = new StudentSubjectCompositeKey(student.getId_student(), subject.getId_subject());

        StudentSubject deleteStudentSubject = studentSubjectRepository
                .findById(key)
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR));

        studentRepository.findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException(ID_STUDENT_ERROR))
                .getStudentSubjects()
                .remove(deleteStudentSubject);

        subjectRepository.findById(id_subject)
                .orElseThrow(() -> new EntityNotFoundException(ID_SUBJECT_ERROR))
                .getStudentSubjects()
                .remove(deleteStudentSubject);

        studentSubjectRepository.delete(deleteStudentSubject);
    }

    @Override
    public void deleteStudentSubjectsByStudentId(String id_student, List<String> subjects) {
        subjects.forEach(subject -> this.deleteStudentSubjectById(id_student, subject));
    }
}
