package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.book.web.BookDto;
import co.istad.bmsapi.api.book.web.BookFilter;
import co.istad.bmsapi.data.repository.BookRepository;
import co.istad.bmsapi.shared.paging.Paging;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

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

        BookDto bookDto = bookMapper.toBookDto(opBook.get());

        return bookDto;

    }
}
