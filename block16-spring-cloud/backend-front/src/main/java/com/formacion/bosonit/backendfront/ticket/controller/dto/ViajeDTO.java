package com.formacion.bosonit.backendfront.ticket.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ViajeDTO {
    private int viaje_id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String status;
}