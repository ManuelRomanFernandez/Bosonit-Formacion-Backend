package com.formacion.bosonit.backendfront.ticket.repository;

import com.formacion.bosonit.backendfront.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
