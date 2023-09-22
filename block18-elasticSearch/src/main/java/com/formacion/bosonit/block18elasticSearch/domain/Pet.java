package com.formacion.bosonit.block18elasticSearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pet {
    String petName;
    String petRace;
    int petAge;
}
