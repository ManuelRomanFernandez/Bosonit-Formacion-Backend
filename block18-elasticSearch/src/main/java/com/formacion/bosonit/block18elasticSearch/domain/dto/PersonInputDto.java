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
public class PersonInputDto {
    private String name;
    private String lastname;
    private int age;
    private List<Pet> pets;
    private int petsNumber;

    public Person inputDtoToPerson(PersonInputDto inputDto){
        return new Person(
                inputDto.getName(),
                inputDto.getLastname(),
                inputDto.getAge(),
                inputDto.getPets()
        );
    }
}
