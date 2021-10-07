package org.melek.tddwithspringframework.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melek.tddwithspringframework.dto.BookMapper;
import org.melek.tddwithspringframework.exception.BookNotFoundException;
import org.melek.tddwithspringframework.model.Book;
import org.melek.tddwithspringframework.repository.BookRepository;
import org.melek.tddwithspringframework.service.BookServiceImp;
import org.melek.tddwithspringframework.util.BookUtil;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImpTest {

    @InjectMocks
    private BookServiceImp bookServiceImp;

    @Mock
    private BookRepository bookRepository;

    @Mock
    BookMapper bookMapper;


    @Test
    void getAllBooks() {
        //Arrange
        when(bookRepository.findAll()).thenReturn(BookUtil.getSampleBookList());
        //Act
        List<Book> bookList = bookServiceImp.getAllBooks();
        //Assert
        assertThat(bookList.size()).isGreaterThan(1);
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    void getBookWithId() {
        //Arrange
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(BookUtil.getSampleBook()));
        //Act
        Book book = bookServiceImp.getBookWithId(1L);
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
            bookServiceImp.getBookWithId(1L);
        });
    }

    //Todo: change with a real function
    @Test
    void sampleTestWithSpy() {
         BookServiceImp spyBookServiceImp = Mockito.spy(bookServiceImp);
         Mockito.doReturn(0).when(spyBookServiceImp).calculate(10);

        int actualResult = spyBookServiceImp.getResult(10);
        assertThat(actualResult).isEqualTo(0);

        int actualResultSecond = spyBookServiceImp.getResult(11);
        assertThat(actualResultSecond).isEqualTo(11*2);
    }
}
