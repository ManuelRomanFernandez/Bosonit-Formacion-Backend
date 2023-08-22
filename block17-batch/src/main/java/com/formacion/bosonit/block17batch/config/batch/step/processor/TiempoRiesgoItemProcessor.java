package com.formacion.bosonit.block17batch.config.batch.step.processor;

import com.formacion.bosonit.block17batch.entity.Tiempo;
import com.formacion.bosonit.block17batch.entity.TiempoRiesgo;
import org.springframework.batch.item.ItemProcessor;

public class TiempoRiesgoItemProcessor implements ItemProcessor<Tiempo, TiempoRiesgo> {
    @Override
    public TiempoRiesgo process(Tiempo tiempo) throws Exception {
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();

        tiempoRiesgo.setTiempoRiesgoId(tiempo.getTiempoId());
        tiempoRiesgo.setTiempo(tiempo);
        tiempoRiesgo.setFechaPrediccion(tiempo.getFecha());

        if (tiempo.getTemperatura() <= 32)
            tiempoRiesgo.setRiesgo("BAJO");
        else if (tiempo.getTemperatura() > 32 && tiempo.getTemperatura() <= 36) {
            tiempoRiesgo.setRiesgo("MEDIO");
        }
        else {
            tiempoRiesgo.setRiesgo("ALTO");
        }

        return tiempoRiesgo;
    }
}
