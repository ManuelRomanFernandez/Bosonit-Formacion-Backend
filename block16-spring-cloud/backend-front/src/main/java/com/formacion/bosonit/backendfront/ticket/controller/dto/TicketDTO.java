package com.formacion.bosonit.backendfront.ticket.controller.dto;

import com.formacion.bosonit.backendfront.ticket.domain.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {
    int ticket_id;
    int passenger_id;
    String passenger_name;
    String passenger_lastname;
    String passenger_email;
    String trip_origin;
    String trip_destination;
    Date departureDate;
    Date arrivalDate;

    public TicketDTO (Ticket ticket){
        this.ticket_id = ticket.getTicket_id();
        this.passenger_id = ticket.getPassenger_id();
        this.passenger_name = ticket.getPassenger_name();
        this.passenger_lastname = ticket.getPassenger_lastname();
        this.passenger_email = ticket.getPassenger_email();
        this.trip_origin = ticket.getTrip_destination();
        this.trip_destination = ticket.getTrip_destination();
        this.departureDate = ticket.getDepartureDate();
        this.arrivalDate = ticket.getArrivalDate();
    }

    public Ticket ticketDtoToTicket(){
        return new Ticket(
                this.ticket_id,
                this.passenger_id,
                this.passenger_name,
                this.passenger_lastname,
                this.passenger_email,
                this.trip_origin,
                this.trip_destination,
                this.departureDate,
                this.arrivalDate
        );
    }
}
