package com.formacion.bosonit.block6personcontrollers.models;

public class Persona {
    private String name;
    private String city;
    private Integer age;

    public Persona() {
    }

    public Persona(String name, String city, Integer age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void multiplyEdadByTwo() {
        this.age *= 2;
    }
}
