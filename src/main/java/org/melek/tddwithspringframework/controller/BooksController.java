package org.melek.tddwithspringframework.controller;

import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.Book;
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
    public List<Book> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return bookList;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookWithId(@PathVariable Long bookId) {
        return new ResponseEntity<>(bookService.getBookWithId(bookId),HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Book saveGivenBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bookNotFoundException(BookNotFoundException bookNotFoundException) {
        return "Book not found!";
    }


}
