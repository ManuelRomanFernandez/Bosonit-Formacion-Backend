package com.formacion.bosonit.backendfront.ticket.application;

import com.formacion.bosonit.backendfront.ticket.controller.TicketFeign;
import com.formacion.bosonit.backendfront.ticket.controller.dto.ClienteDTO;
import com.formacion.bosonit.backendfront.ticket.controller.dto.TicketDTO;
import com.formacion.bosonit.backendfront.ticket.controller.dto.ViajeDTO;
import com.formacion.bosonit.backendfront.ticket.domain.Ticket;
import com.formacion.bosonit.backendfront.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSericeImpl implements TicketService{
    @Autowired
    TicketFeign feign;
    @Autowired
    TicketRepository repository;
    @Override
    public TicketDTO addTicket(int cliente_id, int viaje_id) {
        ClienteDTO cliente = feign.getClienteById(cliente_id).getBody();
        ViajeDTO viaje = feign.getViajeById(viaje_id).getBody();

        Ticket ticket = new Ticket();

        ticket.setPassenger_id(cliente_id);
        if (cliente != null) {
            ticket.setPassenger_name(cliente.getNombre());
            ticket.setPassenger_lastname(cliente.getApellido());
            ticket.setPassenger_email(cliente.getEmail());
        }
        if (viaje != null){
            ticket.setTrip_origin(viaje.getOrigin());
            ticket.setTrip_destination(viaje.getDestination());
            ticket.setDepartureDate(viaje.getDepartureDate());
            ticket.setArrivalDate(viaje.getArrivalDate());
        }

        Ticket newTicket = repository.save(ticket);

        return new TicketDTO(newTicket);
    }
}
