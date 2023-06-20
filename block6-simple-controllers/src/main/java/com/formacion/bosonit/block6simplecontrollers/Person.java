package com.formacion.bosonit.block6simplecontrollers;

public class Person {
    private String name;
    private String city;
    private Integer age;

    public Person(){};

    public Person(String name, String city, Integer age) {
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

    @Override
    public String toString(){
        return "Nombre: " + this.name + ". Poblacion: " + this.city + ". Edad: " + this.age;
    }
}
