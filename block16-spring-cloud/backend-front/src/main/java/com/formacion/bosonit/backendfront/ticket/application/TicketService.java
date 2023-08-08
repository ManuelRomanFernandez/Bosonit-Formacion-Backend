package com.formacion.bosonit.backendfront.ticket.application;

import com.formacion.bosonit.backendfront.ticket.controller.dto.TicketDTO;

public interface TicketService{
    TicketDTO addTicket(int cliente_id, int viaje_id);
}
