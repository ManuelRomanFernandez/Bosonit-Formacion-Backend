package com.bosonit.formacion.block14springsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    Date timestamp;
    Integer httpCode;
    String mensaje;
}
