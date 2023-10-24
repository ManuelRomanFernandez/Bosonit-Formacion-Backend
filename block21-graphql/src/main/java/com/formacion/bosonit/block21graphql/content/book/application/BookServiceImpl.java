package com.formacion.bosonit.block21graphql.content.book.application;

import com.formacion.bosonit.block21graphql.content.author.application.AuthorService;
import com.formacion.bosonit.block21graphql.content.author.domain.Author;
import com.formacion.bosonit.block21graphql.content.book.controller.input.BookInput;
import com.formacion.bosonit.block21graphql.content.book.domain.Book;
import com.formacion.bosonit.block21graphql.content.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository repository;
    @Autowired
    AuthorService authorService;

    @Override
    public Book getBookById(String id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Error: Book does not exists"));
    }

    @Override
    public Book getBookByName(String name) {
        return repository.getBookByName(name).orElseThrow(() -> new NoSuchElementException("Error: Book does not exists"));
    }

    @Override
    public Iterable<Book> getAllBooks(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageRequest);
    }

    @Override
    public Book addBook(BookInput input) {
        Author newAuthor = authorService.addAuthor(input.getFirstName(), input.getLastName());

        Book book = new Book();
        book.setName(input.getName());
        book.setPageCount(input.getPageCount());
        book.setAuthorId(newAuthor.getId());

        return repository.insert(book);
    }

    @Override
    public Book updateBookById(String bookId, BookInput input) {
        Book book = this.getBookById(bookId);

        if (!input.getFirstName().isBlank() | !input.getLastName().isBlank()) {
            authorService.updateAuthorById(book.getAuthorId(), input.getFirstName(), input.getLastName());;
        }

        if (!input.getName().isBlank())
            book.setName(input.getName());
        if (input.getPageCount() != 0)
            book.setPageCount(input.getPageCount());

        return repository.save(book);
    }

    @Override
    public String deleteBookById(String bookId) {
        Book book = this.getBookById(bookId);

        authorService.deleteAuthorById(book.getAuthorId());
        repository.delete(book);

        return "Book Deletion Success";
    }
}
