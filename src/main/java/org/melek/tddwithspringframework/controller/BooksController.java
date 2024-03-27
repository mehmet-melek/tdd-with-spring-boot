package org.melek.tddwithspringframework.controller;

import org.melek.tddwithspringframework.dto.BookDto;
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

    @GetMapping("/name")
    public ResponseEntity<BookDto> getBookWithName(@RequestParam(name = "bookName") String bookName) {
        return new ResponseEntity<>(bookService.getBookWithName(bookName),HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<BookDto> getBookWithId(@RequestParam(name = "id") Long id) {
        return new ResponseEntity<>(bookService.getBookWithId(id),HttpStatus.OK);
    }

 @PostMapping
public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
    return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
}

}
