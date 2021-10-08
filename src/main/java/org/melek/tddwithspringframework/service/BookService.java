package org.melek.tddwithspringframework.service;

import org.melek.tddwithspringframework.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookWithId(Long bookId);
    BookDto addBook(BookDto bookDto);
    BookDto getBookWithName(String bookName);
}
