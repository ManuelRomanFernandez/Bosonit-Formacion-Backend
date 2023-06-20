package com.formacion.bosonit.block6pathvariableheaders.model;

public class Pokemon {
    private String name;
    private String elementType;
    private Integer pokedexNumber;

    public Pokemon() {};

    public Pokemon(String name, String elementType, Integer pokedexNumber) {
        this.name = name;
        this.elementType = elementType;
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public String getElementType() {
        return elementType;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElementType(String firstElementType) {
        this.elementType = firstElementType;
    }

    public void setPokedexNumber(Integer pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }
}
