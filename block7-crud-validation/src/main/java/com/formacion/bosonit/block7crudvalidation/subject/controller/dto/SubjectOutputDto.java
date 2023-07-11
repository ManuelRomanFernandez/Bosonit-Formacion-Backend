package com.formacion.bosonit.block7crudvalidation.subject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectOutputDto {
    String id_subject;
    String name;
    String description;
}
