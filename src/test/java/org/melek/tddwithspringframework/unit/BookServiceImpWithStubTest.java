package org.melek.tddwithspringframework.unit;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.BookMapper;
import org.melek.tddwithspringframework.service.BookServiceImp;
import org.melek.tddwithspringframework.util.StubBookRepository;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BookServiceImpWithStubTest {

    private final BookMapper bookMapper = Mockito.spy(Mappers.getMapper(BookMapper.class));
    private final  BookServiceImp bookServiceImp = new BookServiceImp(new StubBookRepository(),bookMapper);

    @Test
    void getAllBooks() {
        //Arrange
        //Act
        List<BookDto> bookDtoList = bookServiceImp.getAllBooks();
        //Assert
        assertThat(bookDtoList.size()).isGreaterThan(1);
    }

    @Test
    void getBookWithId() {
        //Arrange
        //Act
        BookDto bookDto = bookServiceImp.getBookWithId(1L);
        //Assert
        assertThat(bookDto.getName()).isEqualTo("Clean Code");
    }

}
