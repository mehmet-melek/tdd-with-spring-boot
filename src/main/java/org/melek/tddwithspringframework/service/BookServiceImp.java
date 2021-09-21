package org.melek.tddwithspringframework.service;

import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookWithId(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    //Todo: Delete or change with real method
    public int getResult(Integer value) {
        return calculate(value);
    }
    public int calculate(int value) {
        return value * 2;
    }
}
