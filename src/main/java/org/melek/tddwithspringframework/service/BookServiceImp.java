package org.melek.tddwithspringframework.service;

import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.BookMapper;
import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.entity.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImp(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    public List<BookDto> getAllBooks() {
        return bookMapper.bookListToBookDtoList(bookRepository.findAll());
    }

    public BookDto getBookWithId(Long bookId) {
        return bookMapper.bookToBookDto(bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new));
    }

    @Override
    public BookDto getBookWithName(String bookName) {
        return bookMapper.bookToBookDto(bookRepository.findByName(bookName)
                .orElseThrow(BookNotFoundException::new));
    }

    public BookDto addBook(BookDto bookDto) {
        Book newBook = bookMapper.bookDtoToBook(bookDto);
        return bookMapper.bookToBookDto(bookRepository.save(newBook));
    }

}
