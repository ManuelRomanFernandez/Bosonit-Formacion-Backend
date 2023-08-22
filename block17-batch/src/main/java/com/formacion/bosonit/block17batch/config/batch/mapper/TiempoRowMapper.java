package com.formacion.bosonit.block17batch.config.batch.mapper;

import com.formacion.bosonit.block17batch.entity.Tiempo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiempoRowMapper implements RowMapper<Tiempo> {
    @Override
    public Tiempo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tiempo tiempo = new Tiempo();
        tiempo.setTiempoId(rs.getInt("tiempo_id"));
        tiempo.setProvincia(rs.getString("provincia"));
        tiempo.setTemperatura(rs.getInt("temperatura"));
        tiempo.setFecha(rs.getDate("fecha"));

        return tiempo;
    }
}
