package org.melek.tddwithspringframework.controller;


import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks () {
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.ok(bookList);
    }



}
