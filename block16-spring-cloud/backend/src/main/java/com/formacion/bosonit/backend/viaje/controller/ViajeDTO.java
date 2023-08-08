package com.formacion.bosonit.backend.viaje.controller;

import com.formacion.bosonit.backend.viaje.domain.Viaje;
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

    public ViajeDTO (Viaje viaje){
        this.viaje_id = viaje.getViaje_id();
        this.origin = viaje.getOrigin();
        this.destination = viaje.getDestination();
        this.departureDate = viaje.getDepartureDate();
        this.arrivalDate = viaje.getArrivalDate();
        this.status = viaje.getStatus();
    }

    public Viaje viajeDtoToViaje(){
        return new Viaje(
                this.viaje_id,
                this.origin,
                this.destination,
                this.departureDate,
                this.arrivalDate,
                this.status
        );
    }
}
