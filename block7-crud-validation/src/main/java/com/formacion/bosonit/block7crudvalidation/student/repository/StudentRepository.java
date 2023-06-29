package com.formacion.bosonit.block7crudvalidation.student.repository;

import com.formacion.bosonit.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
