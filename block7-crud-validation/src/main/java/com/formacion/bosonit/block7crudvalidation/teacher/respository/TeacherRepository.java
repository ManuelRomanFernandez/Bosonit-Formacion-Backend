package com.formacion.bosonit.block7crudvalidation.teacher.respository;

import com.formacion.bosonit.block7crudvalidation.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
