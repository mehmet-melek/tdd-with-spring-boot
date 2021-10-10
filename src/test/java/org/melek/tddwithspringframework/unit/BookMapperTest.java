package org.melek.tddwithspringframework.unit;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.melek.tddwithspringframework.dto.BookDto;
import org.melek.tddwithspringframework.dto.BookMapper;
import org.melek.tddwithspringframework.model.entity.Book;
import org.melek.tddwithspringframework.util.BookUtil;


public class BookMapperTest {

    private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
    private BookUtil bookUtil = new BookUtil();


    @Test
    void testBookToBookDto() {
        //Arrange
        Book testBook = bookUtil.getSampleBook();
        //Act
        BookDto resultBookDto = bookMapper.bookToBookDto(testBook);
        //Assert
        Assertions.assertEquals(resultBookDto.getPrice(), testBook.getPrice());
        Assertions.assertEquals(resultBookDto.getName(), testBook.getName());
        Assertions.assertEquals(resultBookDto.getAuthor(), testBook.getAuthor());
        Assertions.assertEquals(resultBookDto.getStock(), testBook.getStock());
        BDDAssertions.then(resultBookDto.getName()).isEqualTo(testBook.getName());
    }

    @Test
    void testBookDtoToBook() {
        //Arrange
        BookDto testBookDto = bookUtil.getSampleBookDto();
        //Act
        Book resultBook = bookMapper.bookDtoToBook(testBookDto);
        //Assert
        Assertions.assertEquals(testBookDto.getName(),resultBook.getName());
        Assertions.assertEquals(testBookDto.getAuthor(),resultBook.getAuthor());
        Assertions.assertEquals(testBookDto.getPrice(),resultBook.getPrice());
        Assertions.assertEquals(testBookDto.getStock(),resultBook.getStock());
        Assertions.assertEquals(null,resultBook.getId());
        BDDAssertions.then(resultBook.getId()).isNull();
    }

}
