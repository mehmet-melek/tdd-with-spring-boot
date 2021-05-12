package org.melek.tddwithspringframework.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.melek.tddwithspringframework.service.BookService;
import org.melek.tddwithspringframework.util.BookUtil;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTests {

    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void setup() {
        bookService = new BookService(bookRepository);
    }

    @Test
    public void getAllBooks() {
        //Arrange
        when(bookRepository.findAll()).thenReturn(BookUtil.getSampleBookList());
        //Act
        List<Book> bookList = bookService.getAllBooks();
        //Assert
        assertThat(bookList.size()).isGreaterThan(1);
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    public void getBookWithId() {
        //Arrange
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(BookUtil.getSampleBook()));
        //Act
        Book book = bookService.getBookWithId(1L);
        //Assert
        assertThat(book.getId()).isEqualTo(1L);
        verify(bookRepository, times(1)).findById(any());
    }

    @Test(expected = BookNotFoundException.class)
    public void getBookWithNonExistId() {
        //Arrange
        //Act
        // Assert
        bookService.getBookWithId(1L);
    }


    //Todo: change with a real function
    @Test
    public void sampleTest() {
        BookService spyBookService = Mockito.spy(bookService);
       // Mockito.doReturn(5).when(spyBookService).calculate(anyInt());
        when(spyBookService.calculate(anyInt())).thenReturn(5);
        Boolean actualResult = spyBookService.getResult(30);
        assertThat(actualResult).isEqualTo(false);
    }
}
