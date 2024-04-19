package io.daalchini.prometheuspoc.data;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    Book toEntity(BookDto bookDto);

    BookDto toDto(Book book);

    List<BookDto> toDto(List<Book> book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(BookDto bookDto, @MappingTarget Book book);
}
