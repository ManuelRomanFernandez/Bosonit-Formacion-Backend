package com.formacion.bosonit.block6personcontrollers.services;

import com.formacion.bosonit.block6personcontrollers.models.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {
    public List<Ciudad> cityList = new ArrayList<>();

    public Ciudad createCiudad(String nombre, Integer habitantes){
        Ciudad ciudad = new Ciudad(nombre, habitantes);
        return ciudad;
    }

    public void addCiudad(Ciudad ciudad){
        cityList.add(ciudad);
    }
}
