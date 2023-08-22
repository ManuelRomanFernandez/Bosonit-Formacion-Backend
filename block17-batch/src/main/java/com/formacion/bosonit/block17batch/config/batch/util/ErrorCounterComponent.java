package com.formacion.bosonit.block17batch.config.batch.util;

import org.springframework.stereotype.Component;

@Component
public class ErrorCounterComponent {
    private ErrorCounter errorCounter = new ErrorCounter();

    public ErrorCounter getErrorCounter() {
        return errorCounter;
    }
}
