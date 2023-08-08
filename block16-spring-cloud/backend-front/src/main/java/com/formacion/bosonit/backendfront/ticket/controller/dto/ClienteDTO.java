package com.formacion.bosonit.backendfront.ticket.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private int cliente_id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;
}
