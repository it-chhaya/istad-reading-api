package co.istad.bmsapi.api.book;

import java.util.List;

import com.github.pagehelper.PageInfo;

import co.istad.bmsapi.api.book.web.BookDto;

public interface BookService {
    
    PageInfo<BookDto> getBooks(int pageNum, int pageSize);

}
