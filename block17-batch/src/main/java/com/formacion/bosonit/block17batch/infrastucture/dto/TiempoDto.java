package com.formacion.bosonit.block17batch.infrastucture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TiempoDto {
    private String provincia;
    private int temperatura;
    private String fecha;
}
