package com.formacion.bosonit.backend.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    String message;

    public EntityNotFoundException(String message){
        this.message = message;
    }

}
