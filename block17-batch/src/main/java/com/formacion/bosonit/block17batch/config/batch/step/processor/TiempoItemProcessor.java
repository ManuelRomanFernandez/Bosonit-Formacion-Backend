package com.formacion.bosonit.block17batch.config.batch.step.processor;

import com.formacion.bosonit.block17batch.entity.Tiempo;
import com.formacion.bosonit.block17batch.infrastucture.dto.TiempoDto;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TiempoItemProcessor implements ItemProcessor<TiempoDto, Tiempo> {
    private static final String DATE_FORMAT_INPUT = "yyyy-MM-dd";
    private static final String DATE_FORMAT_OUTPUT = "dd-MM-yyyy";

    @Override
    public Tiempo process(TiempoDto dto) throws Exception {
        Tiempo tiempo = new Tiempo();
        tiempo.setProvincia(dto.getProvincia());
        tiempo.setTemperatura(dto.getTemperatura());
        tiempo.setFecha(parseFecha(dto.getFecha()));

        return tiempo;
    }

    private Date parseFecha(String fechaString) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat(DATE_FORMAT_INPUT);
        SimpleDateFormat outputFormat = new SimpleDateFormat(DATE_FORMAT_OUTPUT);
        Date fecha = inputFormat.parse(fechaString);
        String formattedFecha = outputFormat.format(fecha);
        return fecha;
    }
}
