package org.melek.tddwithspringframework.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.melek.tddwithspringframework.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDto bookToBookDto(Book book);
}
