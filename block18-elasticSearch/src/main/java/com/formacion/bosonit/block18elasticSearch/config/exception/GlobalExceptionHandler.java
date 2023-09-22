package com.formacion.bosonit.block18elasticSearch.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

/**
 * A global exception handler class responsible for handling specific exceptions and providing
 * customized error responses for them.
 *
 * This class is annotated with {@code @ControllerAdvice} to enable global exception handling
 * throughout the application. It extends {@code ResponseEntityExceptionHandler} to provide
 * a central place for handling exceptions and returning appropriate error responses.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the {@code NoSuchElementException} exception by returning a customized error response
     * with a status code of 404 (Not Found).
     *
     * @param ex The {@code NoSuchElementException} instance that was thrown.
     * @return A {@link HandleError} object representing the customized error response.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public HandleError handleEntityNotFoundException(NoSuchElementException ex) {
        return new HandleError(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
