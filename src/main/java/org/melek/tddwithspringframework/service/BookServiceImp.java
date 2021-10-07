package org.melek.tddwithspringframework.service;

import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.BookMapper;
import org.melek.tddwithspringframework.dto.CreateBookRequest;
import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImp(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    public BookDto getBookWithId(Long bookId) {

        return bookMapper.bookToBookDto(bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new));
    }

    @Override
    public BookDto getBookWithName(String bookName) {
        return null;
    }

    public BookDto addBook(CreateBookRequest createBookRequest) {
        Book newBook = Book.builder()
                .name(createBookRequest.getName())
                .author(createBookRequest.getAuthor())
                .price(createBookRequest.getPrice())
                .stock(createBookRequest.getStock()).build();
        return bookMapper.bookToBookDto(bookRepository.save(newBook));
    }

    //Todo: Delete or change with real method
    public int getResult(Integer value) {
        return calculate(value);
    }

    public int calculate(int value) {
        return value * 2;
    }
}
