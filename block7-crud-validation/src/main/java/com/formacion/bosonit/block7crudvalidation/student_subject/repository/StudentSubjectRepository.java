package com.formacion.bosonit.block7crudvalidation.student_subject.repository;

import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubject;
import com.formacion.bosonit.block7crudvalidation.student_subject.domain.StudentSubjectCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubjectCompositeKey> {
}
