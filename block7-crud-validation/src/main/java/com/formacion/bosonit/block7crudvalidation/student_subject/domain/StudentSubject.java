package com.formacion.bosonit.block7crudvalidation.student_subject.domain;

import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectInputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.controller.dto.StudentSubjectOutputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiante_asignatura")
@Getter
@Setter
public class StudentSubject {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id_student_subject;
    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id_student", nullable = false)
    private Student student;
    @Column(name = "asignatura")
    private String subject;
    @Column(name = "comentarios")
    private String comments;
    @NotNull
    private Date initial_date;
    private Date finish_date;

    public StudentSubject(StudentSubjectInputDto studentSubjectInputDto){
        this.id_student_subject = studentSubjectInputDto.getId_student_subject();
        this.subject = studentSubjectInputDto.getSubject();
        this.comments = studentSubjectInputDto.getComments();
        this.initial_date = studentSubjectInputDto.getInitial_date();
        this.finish_date = studentSubjectInputDto.getFinish_date();
    }

    public StudentSubjectOutputDto studentSubjectToStudentSubjectOutputDto(){
        return new StudentSubjectOutputDto(
                this.id_student_subject,
                this.student.getId_student(),
                this.subject,
                this.comments,
                this.initial_date,
                this.finish_date
        );
    }
}
