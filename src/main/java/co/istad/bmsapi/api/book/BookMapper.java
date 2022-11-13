package co.istad.bmsapi.api.book;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.pagehelper.PageInfo;

import co.istad.bmsapi.api.book.web.BookDto;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toModel(BookDto bookDto);

    BookDto fromModel(Book book);

    List<Book> toListModel(List<BookDto> bookDtoList);

    List<BookDto> fromListModel(List<Book> bookList);
    
    PageInfo<BookDto> fromPageInfoModel(PageInfo<Book> books);

}
