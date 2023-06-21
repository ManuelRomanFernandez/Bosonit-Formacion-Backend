package com.formacion.bosonit.block7crudvalidation.persona.repository;

import com.formacion.bosonit.block7crudvalidation.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
