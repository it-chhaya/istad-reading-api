package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.book.web.BookDto;
import co.istad.bmsapi.api.book.web.BookFilter;
import co.istad.bmsapi.shared.paging.Paging;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookService {

    /**
     * Get all books from database with pagination configuration
     * @param pageNum is used to configure the pagination
     * @param pageSize is used to configure the pagination
     * @return List<BookDto>
     */
    PageInfo<BookDto> getAllBooks(BookFilter bookFilter, int pageNum, int pageSize);


    /**
     * Get book from database with the specified ID
     * @param id is the identifier of bood
     * @return BookDto
     */
    BookDto getBookById(Long id);

}
