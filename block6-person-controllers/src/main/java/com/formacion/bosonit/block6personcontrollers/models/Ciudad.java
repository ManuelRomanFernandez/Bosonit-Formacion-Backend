package com.formacion.bosonit.block6personcontrollers.models;

public class Ciudad {
    private String name;
    private Integer population;

    public Ciudad() {
    }

    public Ciudad(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
