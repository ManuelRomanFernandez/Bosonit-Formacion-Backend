package com.formacion.bosonit.block21graphql.content.book.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private String id;
    private String name;
    private int pageCount;
    private String authorId;
}
