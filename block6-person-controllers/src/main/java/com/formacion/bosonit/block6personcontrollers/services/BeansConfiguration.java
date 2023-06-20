package com.formacion.bosonit.block6personcontrollers.services;

import com.formacion.bosonit.block6personcontrollers.models.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    @Qualifier("bean1")
    Persona bean1() {
        var p = new Persona("Jaimito", "DisneyLand", 6);
        return p;
    }
    @Bean
    @Qualifier("bean2")
    Persona bean2() {
        var p = new Persona("Jorgito", "Castillo Disney", 7);
        return p;
    }
    @Bean
    @Qualifier("bean3")
    Persona bean3() {
        var p = new Persona("Juanito", "Tokyo", 8);
        return p;
    }
}
