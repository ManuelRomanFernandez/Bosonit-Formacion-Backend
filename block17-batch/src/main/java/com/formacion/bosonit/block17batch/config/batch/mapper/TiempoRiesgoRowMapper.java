package com.formacion.bosonit.block17batch.config.batch.mapper;

import com.formacion.bosonit.block17batch.entity.Tiempo;
import com.formacion.bosonit.block17batch.entity.TiempoRiesgo;
import com.formacion.bosonit.block17batch.infrastucture.repository.TiempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiempoRiesgoRowMapper implements RowMapper<TiempoRiesgo> {
    @Autowired
    TiempoRepository repository;

    @Override
    public TiempoRiesgo mapRow(ResultSet rs, int rowNum) throws SQLException {
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setTiempoRiesgoId(rs.getInt("tiempo_riesgo_id"));
        tiempoRiesgo.setFechaPrediccion(rs.getDate("fecha_prediccion"));
        tiempoRiesgo.setRiesgo(rs.getString("riesgo"));

        Tiempo tiempo = repository.findById(rs.getInt("tiempo_id")).orElseThrow();

        tiempoRiesgo.setTiempo(tiempo);

        return tiempoRiesgo;
    }
}
