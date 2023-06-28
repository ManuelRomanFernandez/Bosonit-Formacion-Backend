package com.formacion.bosonit.block7crudvalidation.exception;

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
