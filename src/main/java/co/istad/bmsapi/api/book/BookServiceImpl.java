package co.istad.bmsapi.api.book;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import co.istad.bmsapi.api.book.web.BookDto;
import co.istad.bmsapi.api.book.web.SavedBookDto;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.file.FileServiceImpl;
import co.istad.bmsapi.api.genre.Genre;
import co.istad.bmsapi.api.genre.GenreServiceImpl;
import co.istad.bmsapi.data.repository.BookRepository;
import co.istad.bmsapi.data.repository.GenreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final FileServiceImpl fileServiceImpl;
    private final GenreRepository genreRepository;

    @Value("${file.uri}")
    private String uri;

    @Override
    public BookDto postBook(SavedBookDto savedBookDto) {
        
        Book book = bookMapper.toSavedModel(savedBookDto);
        book.setStarRating(0F);
        book.setCover(new File(savedBookDto.getFileId()));
        book.setIsEnabled(savedBookDto.getIsPublic());

        if (savedBookDto.getIsPublic()) {
            book.setDatePublished(new Date(System.currentTimeMillis()));
        }

        bookRepository.insert(book);

        // Define book genre
        List<Genre> genres = new ArrayList<>();
        for (Integer genreId : savedBookDto.getGenreIds()) {
            bookRepository.insertBookGenre(book.getId(), genreId);
            genres.add(genreRepository.selectWhereId(genreId));
        }
        book.setGenres(genres);

        System.out.println(book);

        BookDto bookDto = bookMapper.fromModel(book);
        bookDto.setCover(fileServiceImpl.getFileById(book.getCover().getId()));

        return bookDto;
    }


    @Override
    public BookDto putBook(Long id, SavedBookDto savedBookDto) {
        
        Book book = bookMapper.toSavedModel(savedBookDto);
        book.setId(id);
        book.setCover(new File(savedBookDto.getFileId()));
        book.setIsEnabled(savedBookDto.getIsPublic());

        if (savedBookDto.getIsPublic()) {
            book.setDatePublished(new Date(System.currentTimeMillis()));
        }

        bookRepository.update(book);
        bookRepository.deleteBookGenre(book.getId());

        // Define book genre
        List<Genre> genres = new ArrayList<>();
        for (Integer genreId : savedBookDto.getGenreIds()) {
            bookRepository.insertBookGenre(book.getId(), genreId);
            genres.add(genreRepository.selectWhereId(genreId));
        }
        book.setGenres(genres);

        return this.getBookById(book.getId());
    }


    @Override
    public PageInfo<BookDto> getBooks(int pageNum, int pageSize) {
        
        Page<Book> bookList = PageHelper.startPage(pageNum, pageSize)
            .doSelectPage(() -> bookRepository.select());

        System.out.println(bookList);
        PageInfo<Book> bookListPageInfo = new PageInfo<>(bookList);

        PageInfo<BookDto> bookDtoListPageInfo = bookMapper.fromPageInfoModel(bookListPageInfo);

        for (BookDto bookDto : bookDtoListPageInfo.getList()) {
            String fileName = bookDto.getCover().getUuid() + "." + bookDto.getCover().getExtension();
            String fileUri = uri + fileName;
            bookDto.getCover().setName(fileName);
            bookDto.getCover().setUri(fileUri);
        }
        
        return bookDtoListPageInfo;
    }


    @Override
    public BookDto getBookById(Long id) {

        Book book = bookRepository.selectById(id);

        BookDto bookDto = bookMapper.fromModel(book);
        bookDto.setCover(fileServiceImpl.getFileById(book.getCover().getId()));

        return bookDto;
    }
    
}
