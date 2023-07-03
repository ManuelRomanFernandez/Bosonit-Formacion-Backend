package com.formacion.bosonit.block7crudvalidation.student.domain;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "estudiante")
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(generator="system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id_student;
    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", unique = true)
    public Persona persona;
    @Column(name = "horas_semanales")
    public Integer num_hours_week;
    @Column(name = "comentarios")
    public String comments;
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    public Teacher teacher;
    @Column(name = "rama")
    public String branch;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<StudentSubject> studentSubjects;
}
