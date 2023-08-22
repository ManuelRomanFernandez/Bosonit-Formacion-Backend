package com.formacion.bosonit.block17batch.config.batch.mapper;

import com.formacion.bosonit.block17batch.entity.Medicion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicionRowMapper implements RowMapper<Medicion> {
    @Override
    public Medicion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Medicion medicion = new Medicion();
        medicion.setMedicionId(rowNum);
        medicion.setProvincia(rs.getString("provincia"));
        medicion.setMes(rs.getInt("mes"));
        medicion.setYear(rs.getInt("year"));
        medicion.setNumeroMediciones(rs.getInt("numero_mediciones"));
        medicion.setTemperaturaMedia(rs.getFloat("temperatura_media"));

        return medicion;
    }
}
