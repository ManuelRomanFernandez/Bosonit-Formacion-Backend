package com.formacion.bosonit.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public CustomError handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        return new CustomError(new Date(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
