package org.melek.tddwithspringframework.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.melek.tddwithspringframework.service.BookService;
import org.melek.tddwithspringframework.util.BookUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;


    @Test
    void getAllBooks() {
        //Arrange
        when(bookRepository.findAll()).thenReturn(BookUtil.getSampleBookList());
        //Act
        List<Book> bookList = bookService.getAllBooks();
        //Assert
        assertThat(bookList.size()).isGreaterThan(1);
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    void getBookWithId() {
        //Arrange
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(BookUtil.getSampleBook()));
        //Act
        Book book = bookService.getBookWithId(1L);
        //Assert
        assertThat(book.getId()).isEqualTo(1L);
        verify(bookRepository, times(1)).findById(any());
    }

    @Test()
    void getBookWithNonExistId() {
        //Arrange
        //Act
        // Assert
        assertThrows(BookNotFoundException.class,() -> {
            bookService.getBookWithId(1L);
        });
    }

    //Todo: change with a real function
    @Test
    void sampleTestWithSpy() {
         BookService spyBookService = Mockito.spy(bookService);
         Mockito.doReturn(0).when(spyBookService).calculate(10);
         Mockito.doCallRealMethod().when(spyBookService).calculate(11);

        int actualResult = spyBookService.getResult(10);
        assertThat(actualResult).isEqualTo(0);

        int actualResultSecond = spyBookService.getResult(11);
        assertThat(actualResultSecond).isEqualTo(11*2);
    }
}
