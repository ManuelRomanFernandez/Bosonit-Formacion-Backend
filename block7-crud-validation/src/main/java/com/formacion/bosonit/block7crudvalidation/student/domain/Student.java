package com.formacion.bosonit.block7crudvalidation.student.domain;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentInputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.controller.dto.StudentSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiante")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(generator="system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id_student;
    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", unique = true)
    private Persona persona;
    @Column(name = "horas_semanales")
    private Integer num_hours_week;
    @Column(name = "comentarios")
    private String comments;
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;
    @Column(name = "rama")
    private String branch;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentSubject> studentSubjects;

    public Student(StudentInputDto studentInputDto){
        this.id_student = studentInputDto.getId_student();
        this.num_hours_week = studentInputDto.getNum_hours_week();
        this.comments = studentInputDto.getComments();
        this.branch = studentInputDto.getBranch();
    }

    public StudentOutputDto studentToStudentOutputDto(){
        return new StudentOutputDto(
                this.id_student,
                this.persona.getId_persona(),
                this.persona.getUsuario(),
                this.persona.getPassword(),
                this.persona.getName(),
                this.persona.getSurname(),
                this.persona.getCompany_email(),
                this.persona.getPersonal_email(),
                this.persona.getCity(),
                this.persona.getActive(),
                this.persona.getCreated_date(),
                this.persona.getImagen(),
                this.persona.getTermination_date(),
                this.num_hours_week,
                this.comments,
                this.teacher != null ? this.teacher.getId_teacher() : null,
                this.branch
        );
    }

    public StudentSimpleOutputDto studentToStudentSimpleOutputDto(){
        return new StudentSimpleOutputDto(
                this.id_student,
                this.persona.getId_persona(),
                this.num_hours_week,
                this.comments,
                this.teacher != null ? this.teacher.getId_teacher() : null,
                this.branch
        );
    }
}
