package com.formacion.bosonit.block7crudvalidation.subject.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class SubjectInputDto {
    private String id_subject;
    @NotNull(message = "name no puede ser null")
    private String name;
    private String description;
}
