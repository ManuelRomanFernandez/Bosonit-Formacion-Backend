package com.formacion.bosonit.block7crud.controller.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDto {
    @Id
    Integer id;
    String nombre;
    Integer edad;
    String poblacion;
}
