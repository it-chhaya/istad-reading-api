package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.book.web.*;
import com.github.pagehelper.PageInfo;

public interface BookService {


    /**
     * Change book cover and return the uri of book cover
     * @param id is the unique identifier of book
     * @param coverDto is requested object from client
     * @return String
     */
    String changeCoverById(Long id, CoverDto coverDto);


    /**
     * Rate star of book by ID
     * Min 0 to Max 5
     * @param id is the unique identifier of book
     * @param starRatingDto is requested object from client
     * @return BookDto
     */
    BookDto rateStarById(Long id, StarRatingDto starRatingDto);


    /**
     * Save book into database
     * @param savedBookDto is requested object from client
     * @return BookDto
     */
    BookDto save(SavedBookDto savedBookDto);


    /**
     * Get all books from database with pagination configuration
     * @param pageNum is used to configure the pagination
     * @param pageSize is used to configure the pagination
     * @return List<BookDto>
     */
    PageInfo<BookDto> getAllBooks(BookFilter bookFilter, int pageNum, int pageSize);


    /**
     * Get book from database with the specified ID
     * @param id is the identifier of book
     * @return BookDto
     */
    BookDto getBookById(Long id);


    /**
     * Delete book from database with the specified ID
     * @param id is the identifier of book
     */
    void deleteBookById(Long id);

}
