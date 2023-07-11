package com.formacion.bosonit.block7crudvalidation.subject.domain;

import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "asignatura")
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id_subject;
    @Column(name = "asignatura")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<StudentSubject> studentSubjects;
}
