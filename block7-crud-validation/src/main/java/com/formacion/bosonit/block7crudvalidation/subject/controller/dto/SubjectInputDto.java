package com.formacion.bosonit.block7crudvalidation.subject.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInputDto {
    String id_subject;
    @NotNull(message = "name no puede ser null")
    String name;
    String description;
}
