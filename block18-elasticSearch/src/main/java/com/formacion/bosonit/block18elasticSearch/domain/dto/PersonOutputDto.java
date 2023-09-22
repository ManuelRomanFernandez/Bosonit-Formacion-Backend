package com.formacion.bosonit.block18elasticSearch.domain.dto;

import com.formacion.bosonit.block18elasticSearch.domain.Person;
import com.formacion.bosonit.block18elasticSearch.domain.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonOutputDto {
    private String id;
    private String name;
    private String lastname;
    private int age;
    private List<Pet> pets;

    public PersonOutputDto personToOutputDto(Person person){
        return new PersonOutputDto(
                person.getId(),
                person.getName(),
                person.getLastname(),
                person.getAge(),
                person.getPets()
        );
    }
}
