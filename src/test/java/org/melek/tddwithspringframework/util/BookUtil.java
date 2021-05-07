package org.melek.tddwithspringframework.util;

import org.melek.tddwithspringframework.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookUtil {

    public static List<Book> getSampleBookList() {
        Book book1 = Book.builder()
                .name("Clean Code")
                .author("Robert Cecil")
                .price(10.0)
                .stock(10).build();
        Book book2 = Book.builder()
                .name("Refactoring")
                .author("Kent Beck")
                .price(11.1)
                .stock(15).build();
        Book book3 = Book.builder()
                .name("Continuous Delivery")
                .author("David Farley")
                .price(15.5)
                .stock(10).build();
        return new ArrayList<>(Arrays.asList(book1, book2, book3));
    }

    public static Book getSampleBook() {
        return Book.builder()
                .id(1L)
                .name("Clean Code")
                .author("Robert Cecil")
                .price(10.0)
                .stock(10).build();
    }
}
