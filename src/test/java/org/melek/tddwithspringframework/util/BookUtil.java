package org.melek.tddwithspringframework.util;

import lombok.Getter;
import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.model.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class BookUtil {

    private  List<Book> sampleBookList;
    private  List<BookDto> sampleBookDtoList;
    private  Book sampleBook;
    private  BookDto sampleBookDto;


    public BookUtil() {
        createSampleBookDtoList();
        createSampleBookList();
        createSampleBookDto();
        createSampleBook();
    }

    private void createSampleBookList() {
        Book book1 = Book.builder()
                .id(1L)
                .name("Clean Code")
                .author("Robert Cecil")
                .price(10.0)
                .stock(10).build();
        Book book2 = Book.builder()
                .id(2L)
                .name("Refactoring")
                .author("Kent Beck")
                .price(11.1)
                .stock(15).build();
        Book book3 = Book.builder()
                .id(3L)
                .name("Continuous Delivery")
                .author("David Farley")
                .price(15.5)
                .stock(10).build();
        sampleBookList = new ArrayList<>(Arrays.asList(book1, book2, book3));
    }

    private void createSampleBookDtoList() {
        BookDto book1 = BookDto.builder()
                .name("Clean Code")
                .author("Robert Cecil")
                .price(10.0)
                .stock(10).build();
        BookDto book2 = BookDto.builder()
                .name("Refactoring")
                .author("Kent Beck")
                .price(11.1)
                .stock(15).build();
        BookDto book3 = BookDto.builder()
                .name("Continuous Delivery")
                .author("David Farley")
                .price(15.5)
                .stock(10).build();
        sampleBookDtoList = new ArrayList<>(Arrays.asList(book1, book2, book3));
    }

    private void createSampleBook() {
        sampleBook = Book.builder()
                .id(1L)
                .name("Clean Code")
                .author("Robert Cecil")
                .price(10.0)
                .stock(10).build();
    }

    private void createSampleBookDto() {
        sampleBookDto = BookDto.builder()
                .name("Clean Code")
                .author("Robert Cecil")
                .price(10.0)
                .stock(10).build();
    }
}
