package com.formacion.bosonit.block17batch.config.batch.step.processor;

import com.formacion.bosonit.block17batch.entity.Medicion;
import org.springframework.batch.item.ItemProcessor;

public class MedicionItemProcessor implements ItemProcessor<Medicion, Medicion> {
    @Override
    public Medicion process(Medicion medicion) throws Exception {
        if (medicion.getTemperaturaMedia() < 32)
            medicion.setRiesgo("BAJO");
        else if (medicion.getTemperaturaMedia() >= 32 && medicion.getTemperaturaMedia() <= 36) {
            medicion.setRiesgo("MEDIO");
        }
        else {
            medicion.setRiesgo("ALTO");
        }
        return medicion;
    }
}
