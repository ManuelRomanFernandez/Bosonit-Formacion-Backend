package com.formacion.bosonit.backend.viaje.domain;

import com.formacion.bosonit.backend.cliente.domain.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native")
    private int viaje_id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String status;
    @ManyToMany(mappedBy = "viajes")
    private List<Cliente> passenger;

    public Viaje(int viaje_id, String origin, String destination, Date departureDate, Date arrivalDate, String status) {
        this.viaje_id = viaje_id;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.status = status;
    }
}
