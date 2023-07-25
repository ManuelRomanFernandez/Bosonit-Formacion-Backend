package com.formacion.bosonit.block7crudvalidation.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private final String message;

    public EntityNotFoundException(String message){
        this.message = message;
    }

}
