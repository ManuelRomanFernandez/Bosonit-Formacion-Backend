package com.formacion.bosonit.block21graphql.content.book.repository;

import com.formacion.bosonit.block21graphql.content.book.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    @Query("{name:?0}")
    Optional<Book> getBookByName(String name);
}
