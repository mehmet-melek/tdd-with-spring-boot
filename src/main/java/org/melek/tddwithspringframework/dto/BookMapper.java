package org.melek.tddwithspringframework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.melek.tddwithspringframework.model.entity.Book;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDto bookToBookDto(Book book);
    List<BookDto> bookListToBookDtoList(List<Book> bookList);
    Book bookDtoToBook(BookDto bookDto);
}
