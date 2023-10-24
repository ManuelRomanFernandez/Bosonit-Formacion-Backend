package com.formacion.bosonit.block21graphql.content.author.application;

import com.formacion.bosonit.block21graphql.content.author.domain.Author;
import com.formacion.bosonit.block21graphql.content.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    AuthorRepository repository;

    @Override
    public Author getAuthorByid(String id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Error: Author does not exists"));
    }

    @Override
    public Author addAuthor(String firstname, String lastName) {
        Author author = new Author();
        author.setFirstName(firstname);
        author.setLastName(lastName);

        return repository.insert(author);
    }

    @Override
    public void updateAuthorById(String id, String firstname, String lastname) {
        Author author = this.getAuthorByid(id);

        if (!firstname.isBlank())
            author.setFirstName(firstname);

        if (!lastname.isBlank())
            author.setLastName(lastname);

        repository.save(author);
    }

    @Override
    public void deleteAuthorById(String id) {
        Author author = this.getAuthorByid(id);
        repository.delete(author);
    }
}