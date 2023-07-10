package com.formacion.bosonit.block11uploaddownloadfiles.fichero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fichero {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "nombre_archivo")
    private String filename;
    @Column(name = "fecha_subida")
    private Date upload_date;
    @Column(name = "categoria")
    private String category;
}
