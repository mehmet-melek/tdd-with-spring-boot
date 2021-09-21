package org.melek.tddwithspringframework.service;

import org.melek.tddwithspringframework.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookWithId(Long bookId);
    Book addBook(Book book);
}
