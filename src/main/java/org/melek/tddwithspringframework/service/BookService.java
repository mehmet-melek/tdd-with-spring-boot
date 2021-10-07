package org.melek.tddwithspringframework.service;

import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.CreateBookRequest;
import org.melek.tddwithspringframework.model.Book;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookWithId(Long bookId);
    BookDto addBook(CreateBookRequest createBookRequest);
    BookDto getBookWithName(String bookName);
}
