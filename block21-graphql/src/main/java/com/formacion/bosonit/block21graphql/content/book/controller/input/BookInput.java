package com.formacion.bosonit.block21graphql.content.book.controller.input;

import lombok.Data;

@Data
public class BookInput {
    private String name;
    private int pageCount;
    private String firstName;
    private String lastName;
}
