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
@AllArgsConstructor
@NoArgsConstructor
public class Tiempo {
    @Id
    @Column(name = "tiempo_id")
    @GeneratedValue
    private int tiempoId;
    private String provincia;
    private int temperatura;
    private Date fecha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tiempo")
    private TiempoRiesgo riesgo;

    @Override
    public String toString() {
        int year = this.fecha.getYear() + 1900;
        int mes = this.fecha.getMonth() + 1;
        int dia = this.fecha.getDate();

        return this.provincia + ";" + this.temperatura + ";" + year + "-" + mes + "-" + dia;
    }
}
