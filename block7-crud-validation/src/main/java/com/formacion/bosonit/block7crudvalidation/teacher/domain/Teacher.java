package com.formacion.bosonit.block7crudvalidation.teacher.domain;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "profesor")
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id_teacher;
    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", unique = true)
    public Persona persona;
    @Column(name = "comentarios")
    public String comments;
    @Column(name = "rama")
    public String branch;
    @OneToMany
    public Set<Student> students;
}
