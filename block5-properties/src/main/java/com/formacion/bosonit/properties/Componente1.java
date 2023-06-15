package com.formacion.bosonit.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
public class Componente1 implements CommandLineRunner {

    @Value("${greeting}")
    String greeting;

    @Value("${my.number}")
    Integer number;

    @Value("${NEW_PROPERTY:new.property no tiene valor}")
    String property = System.getenv("NUMBER_OF_PROCESSORS");

    @Override
    public void run(String... args) throws Exception {
        System.out.println("El valor de greeting es: "  + greeting);
        System.out.println("El valor de my.number es: "  + number);
        System.out.println("El valor de new.property es: " + property);
    }
}
