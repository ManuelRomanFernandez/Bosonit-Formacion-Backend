package com.formacion.bosonit.block17batch.config.batch.step.writer;

import com.formacion.bosonit.block17batch.entity.Tiempo;
import com.formacion.bosonit.block17batch.entity.TiempoRiesgo;
import com.formacion.bosonit.block17batch.infrastucture.repository.TiempoRepository;
import com.formacion.bosonit.block17batch.infrastucture.repository.TiempoRiesgoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TiempoRiesgoItemWriter implements ItemWriter<TiempoRiesgo> {
    @Autowired
    TiempoRiesgoRepository tiempoRiesgoRepository;
    @Autowired
    TiempoRepository tiempoRepository;

    @Override
    public void write(List<? extends TiempoRiesgo> list) throws Exception {
        list.forEach(tiempoRiesgo -> {
            Tiempo tiempo = tiempoRepository
                    .findById(tiempoRiesgo.getTiempo().getTiempoId())
                    .orElseThrow(() -> new RuntimeException("No existe el tiempo con id: " + tiempoRiesgo.getTiempo().getTiempoId()));

            tiempo.setRiesgo(tiempoRiesgo);

            tiempoRepository.save(tiempo);
            tiempoRiesgoRepository.save(tiempoRiesgo);
        });
    }
}
