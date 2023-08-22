package com.formacion.bosonit.block17batch.config.batch.step.writer;

import com.formacion.bosonit.block17batch.config.batch.util.ErrorCounterComponent;
import com.formacion.bosonit.block17batch.entity.Tiempo;
import com.formacion.bosonit.block17batch.infrastucture.repository.TiempoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class TiempoItemWriter implements ItemWriter<Tiempo> {
    @Autowired
    TiempoRepository repository;
    @Autowired
    ErrorCounterComponent errorCounterComponent;
    private LocalDateTime now = null;
    private boolean isJobFailed = false;

    @Override
    public void write(List<? extends Tiempo> list) throws Exception {
        if (!isJobFailed) {
            if (now == null)
                now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String fileName = "logs/registros_erroneos_" + now.format(formatter) + ".csv";

            File file = new File(fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            list.forEach(tiempo -> {
                if (tiempo.getTemperatura() >= 50 || tiempo.getTemperatura() <= -20) {
                    try {
                        writer.write(tiempo.toString());
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    errorCounterComponent.getErrorCounter().increment();
                } else {
                    repository.save(tiempo);
                }

            });

            if (errorCounterComponent.getErrorCounter().getCount() >= 100) {
                repository.deleteAll();
                isJobFailed = true;
                log.warn("Se han detectado 100 o más errores en los registros");
            }

            writer.close();
        }
    }
}
