package com.formacion.bosonit.block21graphql.content.author.application;

import com.formacion.bosonit.block21graphql.content.author.domain.Author;

public interface AuthorService {
    Author getAuthorByid(String id);
    Author addAuthor(String firstname, String lastName);
    void updateAuthorById(String id, String firstname, String lastname);
    void deleteAuthorById(String id);
}
