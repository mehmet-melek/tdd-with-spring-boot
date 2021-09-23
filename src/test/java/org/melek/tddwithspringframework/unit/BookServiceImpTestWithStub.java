package org.melek.tddwithspringframework.unit;


import org.junit.jupiter.api.Test;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.service.BookServiceImp;
import org.melek.tddwithspringframework.util.StubBookRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class BookServiceImpTestWithStub {

    private final  BookServiceImp bookServiceImp = new BookServiceImp(new StubBookRepository());

    @Test
    void getAllBooks() {
        //Arrange
        //Act
        List<Book> bookList = bookServiceImp.getAllBooks();
        //Assert
        assertThat(bookList.size()).isGreaterThan(1);
    }

    @Test
    void getBookWithId() {
        //Arrange
        //Act
        Book book = bookServiceImp.getBookWithId(1L);
        //Assert
        assertThat(book.getId()).isEqualTo(1L);
    }

}
