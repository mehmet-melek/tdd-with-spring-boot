package org.melek.tddwithspringframework.util;

import org.melek.tddwithspringframework.model.Book;
import java.util.Arrays;
import java.util.List;

public class BookUtil {

    private Book book;

    public static List<Book> getSampleBookList() {

        Book book1 = Book.builder()
                .name("Dava")
                .author("Kafka")
                .price(10.0)
                .stock(10).build();
        Book book2 = Book.builder()
                .name("Mecburiyet")
                .author("Zwaig")
                .price(11.1)
                .stock(15).build();
        Book book3 = Book.builder()
                .name("Santraç")
                .author("Zwaig")
                .price(15.5)
                .stock(10).build();

        List<Book> bookList = Arrays.asList(book1, book2, book3);

        return bookList;
    }

}
