package com.formacion.bosonit.block21graphql.content.book.application;

import com.formacion.bosonit.block21graphql.content.book.controller.input.BookInput;
import com.formacion.bosonit.block21graphql.content.book.domain.Book;

public interface BookService {
    Book getBookById(String id);
    Book getBookByName(String name);
    Iterable<Book> getAllBooks(int pageNumber, int pageSize);
    Book addBook(BookInput input);
    Book updateBookById(String bookId, BookInput input);
    String deleteBookById(String bookId);
}
