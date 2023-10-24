package com.formacion.bosonit.block21graphql.content.book.controller;

import com.formacion.bosonit.block21graphql.content.author.application.AuthorService;
import com.formacion.bosonit.block21graphql.content.book.application.BookService;
import com.formacion.bosonit.block21graphql.content.book.controller.input.BookInput;
import com.formacion.bosonit.block21graphql.content.author.domain.Author;
import com.formacion.bosonit.block21graphql.content.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @QueryMapping
    public Book getBookById(@Argument String id) {
        return bookService.getBookById(id);
    }

    @QueryMapping
    public Book getBookByName(@Argument String name) {
        return bookService.getBookByName(name);
    }

    @QueryMapping
    public Iterable<Book> getAllBooks(@Argument int pageNumber, @Argument int pageSize){
        return bookService.getAllBooks(pageNumber, pageSize);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput input){
        return bookService.addBook(input);
    }

    @MutationMapping
    public Book updateBookById(@Argument String bookId, @Argument BookInput input){
        return bookService.updateBookById(bookId, input);
    }

    @MutationMapping
    public String deleteBookById(@Argument String bookId) {
        return bookService.deleteBookById(bookId);
    }

    @SchemaMapping
    public Author author(Book book) {
        return authorService.getAuthorByid(book.getAuthorId());
    }
}
