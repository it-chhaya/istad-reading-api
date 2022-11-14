package co.istad.bmsapi.api.book;

import java.util.List;

import com.github.pagehelper.PageInfo;

import co.istad.bmsapi.api.book.web.BookDto;
import co.istad.bmsapi.api.book.web.SavedBookDto;

public interface BookService {

    BookDto postBook(SavedBookDto savedBookDto);

    BookDto putBook(Long id, SavedBookDto savedBookDto);
    
    PageInfo<BookDto> getBooks(int pageNum, int pageSize);

    BookDto getBookById(Long id);

}
