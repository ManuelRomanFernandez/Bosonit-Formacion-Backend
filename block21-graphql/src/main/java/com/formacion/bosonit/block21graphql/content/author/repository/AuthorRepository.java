package com.formacion.bosonit.block21graphql.content.author.repository;

import com.formacion.bosonit.block21graphql.content.author.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
