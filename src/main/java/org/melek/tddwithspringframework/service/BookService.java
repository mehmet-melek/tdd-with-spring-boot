package org.melek.tddwithspringframework.service;

import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
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

    public void sampleTestableMethod() {


    }

    //Todo: Delete or change with real method
    public Boolean getResult(Integer value) {
        System.out.println(calculate(value));
        return calculate(value) > 20;
    }

    public int calculate(int value) {
        value = value * 2;
        return value;
    }
}
