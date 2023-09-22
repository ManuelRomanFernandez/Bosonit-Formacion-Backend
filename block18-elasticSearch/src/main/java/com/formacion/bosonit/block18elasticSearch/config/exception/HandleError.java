package com.formacion.bosonit.block18elasticSearch.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleError {
    Date timestamp;
    Integer httpCode;
    String message;
}
