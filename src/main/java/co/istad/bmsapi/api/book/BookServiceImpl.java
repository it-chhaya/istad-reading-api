package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.book.web.BookDto;
import co.istad.bmsapi.api.book.web.BookFilter;
import co.istad.bmsapi.api.book.web.SavedBookDto;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.file.FileServiceImpl;
import co.istad.bmsapi.api.file.web.FileDto;
import co.istad.bmsapi.data.repository.BookRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final FileServiceImpl fileServiceImpl;

    @Override
    public BookDto save(SavedBookDto savedBookDto) {

        Book book = bookMapper.fromSavedBookDto(savedBookDto);
        book.setCover(new File(savedBookDto.getFileId()));
        book.setStarRating((short) 0);
        book.setIsEnabled(savedBookDto.getIsPublished());

        try {
            if (book.getId() == null) {
                book.setDatePublished(savedBookDto.getIsPublished() ? LocalDate.now() : null);
                bookRepository.insert(book);

                // Define genre of book
                savedBookDto.getGenreIds().forEach(genreId -> bookRepository.insertBookGenre(book.getId(), genreId));
            } else {
                bookRepository.update(book);

                bookRepository.deleteBookGenresById(book.getId());

                // Update genre of book
                savedBookDto.getGenreIds().forEach(genreId -> bookRepository.insertBookGenre(book.getId(), genreId));
            }
        } catch (Exception e) {
            log.error("Error = {}", e.getMessage());
            String reason = "You cannot save the record.";
            Throwable cause = new Throwable("Server went wrong, please contact developers or try again.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, reason, cause);
        }

        // go to file by ID
        FileDto fileDto = fileServiceImpl.getFileByID(savedBookDto.getFileId());

        BookDto bookDto = bookMapper.toBookDto(book);
        bookDto.setCover(fileDto);

        return bookDto;
    }


    @Override
    public PageInfo<BookDto> getAllBooks(BookFilter bookFilter, int pageNum, int pageSize) {

        Book book = bookMapper.fromBookFilter(bookFilter);

        Page<Book> bookList = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> bookRepository.select(book));
        PageInfo<Book> bookListPageInfo = new PageInfo<>(bookList);

        return bookMapper.toBookDtoList(bookListPageInfo);

    }


    @Override
    public BookDto getBookById(Long id) {

        Optional<Book> opBook = bookRepository.selectById(id);

        if (opBook.isEmpty()) {
            String reason = "Book with ID = " + id + " is not found in DB";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        return bookMapper.toBookDto(opBook.get());
    }


    @Override
    public void deleteBookById(Long id) {

        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            String reason = "Delete book operation is failed.";
            Throwable cause = new Throwable("Book with ID = " + id + " is not found in DB.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, cause);
        }

    }

}
