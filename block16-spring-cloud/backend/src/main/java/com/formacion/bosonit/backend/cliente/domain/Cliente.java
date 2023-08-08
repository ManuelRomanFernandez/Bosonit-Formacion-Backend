package com.formacion.bosonit.backend.cliente.domain;

import com.formacion.bosonit.backend.viaje.domain.Viaje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native")
    private int cliente_id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;
    @ManyToMany
    private List<Viaje> viajes;

    public Cliente(int cliente_id, String nombre, String apellido, int edad, String email, int telefono) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.telefono = telefono;
    }
}
