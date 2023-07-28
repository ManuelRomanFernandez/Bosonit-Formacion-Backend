package com.formacion.bosonit.backend.cliente.repository;

import com.formacion.bosonit.backend.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
