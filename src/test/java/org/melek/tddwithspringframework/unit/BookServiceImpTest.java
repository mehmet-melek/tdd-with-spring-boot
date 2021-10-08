package org.melek.tddwithspringframework.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.BookMapper;
import org.melek.tddwithspringframework.exception.BookNotFoundException;
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

    @Spy
    private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);


    @Test
    void getAllBooks() {
        //Arrange
        when(bookRepository.findAll()).thenReturn(BookUtil.getSampleBookList());
        //when(bookMapper.booListToBookDtoList(BookUtil.getSampleBookList())).thenReturn(BookUtil.getSampleBookDtoList());
        //Act
        List<BookDto> bookDtoList = bookServiceImp.getAllBooks();
        //Assert
        assertThat(bookDtoList.size()).isGreaterThan(1);
        verify(bookRepository, times(1)).findAll();
        verify(bookMapper,times(1)).booListToBookDtoList(ArgumentMatchers.refEq(BookUtil.getSampleBookList()));

    }

    @Test
    void getBookWithName() {
        //Arrange
        when(bookRepository.findByName(any())).thenReturn(java.util.Optional.ofNullable(BookUtil.getSampleBook()));
        //Act
        BookDto bookDto = bookServiceImp.getBookWithName("Clean Code");
        //Assert
        assertThat(bookDto.getName()).isEqualTo("Clean Code");
        verify(bookRepository, times(1)).findByName(any());
    }

    @Test
    void getBookWithId() {
        //Arrange
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(BookUtil.getSampleBook()));
        //Act
        BookDto bookDto = bookServiceImp.getBookWithId(1L);
        //Assert
        assertThat(bookDto.getName()).isEqualTo("Clean Code");
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
