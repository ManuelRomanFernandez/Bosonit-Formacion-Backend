package com.formacion.bosonit.block17batch.config.batch.step.writer;

import com.formacion.bosonit.block17batch.entity.Medicion;
import com.formacion.bosonit.block17batch.infrastucture.repository.MedicionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MedicionItemWriter implements ItemWriter<Medicion> {
    @Autowired
    MedicionRepository repository;

    @Override
    public void write(List<? extends Medicion> list) throws Exception {
        repository.saveAll(list);
    }
}
