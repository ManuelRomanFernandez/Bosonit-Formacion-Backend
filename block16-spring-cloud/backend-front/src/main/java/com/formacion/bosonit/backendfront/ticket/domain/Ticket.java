package com.formacion.bosonit.backendfront.ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native")
    int ticket_id;
    int passenger_id;
    String passenger_name;
    String passenger_lastname;
    String passenger_email;
    String trip_origin;
    String trip_destination;
    Date departureDate;
    Date arrivalDate;
}
