package com.formacion.bosonit.block7crudvalidation.teacher.domain;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherInputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherOutputDto;
import com.formacion.bosonit.block7crudvalidation.teacher.controller.dto.TeacherSimpleOutputDto;
import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
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
@Table(name = "profesor")
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id_teacher;
    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false, unique = true)
    private Persona persona;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama")
    private String branch;
    @OneToMany
    private Set<Student> students;

    public Teacher(TeacherInputDto teacherInputDto){
        this.id_teacher = teacherInputDto.getId_teacher();
        this.comments = teacherInputDto.getComments();
        this.branch = teacherInputDto.getBranch();
    }

    public TeacherOutputDto teacherToTeacherOutputDto(){
        return new TeacherOutputDto(
                this.id_teacher,
                this.persona.getId_persona(),
                this.persona.getUsuario(),
                this.persona.getName(),
                this.persona.getSurname(),
                this.persona.getCompany_email(),
                this.persona.getPersonal_email(),
                this.persona.getCity(),
                this.persona.getActive(),
                this.persona.getCreated_date(),
                this.persona.getImagen(),
                this.persona.getTermination_date(),
                this.comments,
                this.branch
        );
    }

    public TeacherSimpleOutputDto teacherToTeacherSimpleOutputDto(){
        return new TeacherSimpleOutputDto(
                this.id_teacher,
                this.persona.getId_persona(),
                this.comments,
                this.branch
        );
    }
}
