package com.bosonit.formacion.block14springsecurity.repository;


import com.bosonit.formacion.block14springsecurity.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
