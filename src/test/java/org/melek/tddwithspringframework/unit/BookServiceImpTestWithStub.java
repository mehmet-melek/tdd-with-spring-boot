package org.melek.tddwithspringframework.unit;


import org.junit.jupiter.api.Test;
import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.BookMapper;
import org.melek.tddwithspringframework.service.BookServiceImp;
import org.melek.tddwithspringframework.util.StubBookRepository;
import org.mockito.Mockito;


import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class BookServiceImpTestWithStub {

    private final BookMapper bookMapper = Mockito.spy(BookMapper.class);
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
