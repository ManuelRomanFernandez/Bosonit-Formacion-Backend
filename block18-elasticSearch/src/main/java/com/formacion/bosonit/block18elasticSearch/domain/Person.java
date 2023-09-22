package com.formacion.bosonit.block18elasticSearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "person")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    private String id;
    private String name;
    private String lastname;
    private int age;
    @Field(type = FieldType.Nested)
    private List<Pet> pets;

    public Person(String name, String lastname, int age, List<Pet> pets) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.pets = pets;
    }
}
