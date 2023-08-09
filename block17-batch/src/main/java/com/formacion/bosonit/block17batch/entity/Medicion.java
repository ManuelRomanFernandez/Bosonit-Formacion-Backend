package com.formacion.bosonit.block17batch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicion {
    @Id
    @Column(name = "medicion_id")
    private int medicionId;
    private String provincia;
    private int mes;
    private int year;
    @Column(name = "numero_mediciones")
    private int numeroMediciones;
    @Column(name = "temperatura_media")
    private float temperaturaMedia;
    private String riesgo;
}
