package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.book.web.*;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.file.FileServiceImpl;
import co.istad.bmsapi.api.file.web.FileDto;
import co.istad.bmsapi.data.repository.BookRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.uri}")
    private String baseUri;


    @Override
    public String changeCoverById(Long id, CoverDto coverDto) {

        if (bookRepository.existsById(id)) {
            bookRepository.updateCoverWhereId(id, coverDto.getCoverId());
        } else {
            String reason = "Updating book cover is failed, please try again.";
            Throwable cause = new Throwable(String.format("Book ID = %s is not found in database.", id));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, cause);
        }

        return fileServiceImpl.getFileByID(coverDto.getCoverId()).getUri();
    }

    @Override
    public BookDto rateStarById(Long id, StarRatingDto starRatingDto) {

        if (bookRepository.existsById(id)) {
            bookRepository.updateStarRating(id, starRatingDto.getStarRating());
        } else {
            String reason = "Rating star operation is failed, please try again.";
            Throwable cause = new Throwable(String.format("Book ID = %s is not found in database.", id));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, cause);
        }

        return this.getBookById(id);
    }

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

        PageInfo<BookDto> bookDtoListPageInfo = bookMapper.toBookDtoList(bookListPageInfo);

        for (BookDto bookDto : bookDtoListPageInfo.getList()) {
            String fileName = bookDto.getCover().getUuid() + "." + bookDto.getCover().getExtension();
            String fileUri = baseUri + fileName;
            bookDto.getCover().setName(fileName);
            bookDto.getCover().setUri(fileUri);
        }

        return bookDtoListPageInfo;

    }


    @Override
    public BookDto getBookById(Long id) {

        Optional<Book> opBook = bookRepository.selectById(id);

        if (opBook.isEmpty()) {
            String reason = "Book with ID = " + id + " is not found in DB";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        BookDto bookDto = bookMapper.toBookDto(opBook.get());
        bookDto.getCover().buildNameAndUri(baseUri);

        return bookDto;
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
