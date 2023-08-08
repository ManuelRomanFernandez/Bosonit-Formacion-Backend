package com.formacion.bosonit.block7crudvalidation.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {

    private final String message;

    public UnprocessableEntityException(String message){
        this.message = message;
    }

}
