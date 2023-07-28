package com.formacion.bosonit.backend.viaje.repository;

import com.formacion.bosonit.backend.viaje.domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}
