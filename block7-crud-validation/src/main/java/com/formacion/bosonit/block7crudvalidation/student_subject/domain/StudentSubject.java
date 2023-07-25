package com.formacion.bosonit.block7crudvalidation.student_subject.domain;

import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import com.formacion.bosonit.block7crudvalidation.subject.domain.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@IdClass(StudentSubjectCompositeKey.class)
@Table(name = "estudiante_asignatura")
@NoArgsConstructor
@Getter
@Setter
public class StudentSubject {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id_student")
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_subject", referencedColumnName = "id_subject")
    private Subject subject;
    @Column(name = "comentarios")
    private String comments;
    private Date initial_date;
    private Date finish_date;
}
