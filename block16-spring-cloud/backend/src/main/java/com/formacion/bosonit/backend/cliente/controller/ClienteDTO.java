package com.formacion.bosonit.backend.cliente.controller;

import com.formacion.bosonit.backend.cliente.domain.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
    private int cliente_id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;

    public ClienteDTO(Cliente cliente){
        this.cliente_id = cliente.getCliente_id();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.edad = cliente.getEdad();
        this.email = cliente.getEmail();
        this.telefono = cliente.getTelefono();
    }

    public Cliente clienteDtoToCliente(){
        return new Cliente(
                this.getCliente_id(),
                this.getNombre(),
                this.getApellido(),
                this.getEdad(),
                this.getEmail(),
                this.getTelefono()
        );
    }
}
