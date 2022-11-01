package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.book.web.BookDto;
import co.istad.bmsapi.api.book.web.BookFilter;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    PageInfo<BookDto> toBookDtoList(PageInfo<Book> bookList);

    Book fromBookFilter(BookFilter bookFilter);

    Book fromBookDto(BookDto bookDto);

    BookDto toBookDto(Book book);

}
