package org.melek.tddwithspringframework.dto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.melek.tddwithspringframework.model.entity.Book;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDto bookToBookDto(Book book);
    List<BookDto> booListToBookDtoList(List<Book> bookList);
    Book bookDtoToBook(BookDto bookDto);
}
