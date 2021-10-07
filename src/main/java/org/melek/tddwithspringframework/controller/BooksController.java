package org.melek.tddwithspringframework.controller;

import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.CreateBookRequest;
import org.melek.tddwithspringframework.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/name/{bookName}")
    public ResponseEntity<BookDto> getBookWithId(@PathVariable String bookName) {
        return new ResponseEntity<>(bookService.getBookWithName(bookName),HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BookDto saveGivenBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.addBook(createBookRequest);
    }

}
