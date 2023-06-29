package com.formacion.bosonit.block7crudvalidation.student_subject.domain;

import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
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
    public String id_student_subject;
    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id_student", nullable = false)
    public Student student;
    @Column(name = "asignatura")
    public String subject;
    @Column(name = "comentarios")
    public String comments;
    @NotNull
    public Date initial_date;
    public Date finish_date;
}
