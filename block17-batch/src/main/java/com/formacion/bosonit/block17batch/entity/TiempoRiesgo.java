package com.formacion.bosonit.block17batch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TiempoRiesgo {
    @Id
    private int tiempoRiesgoId;
    private Date fechaPrediccion;
    private String riesgo;
    @OneToOne
    @JoinColumn(name = "tiempo_id")
    private Tiempo tiempo;
}
