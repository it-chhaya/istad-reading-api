package co.istad.bmsapi.api.book;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import co.istad.bmsapi.api.book.web.BookDto;
import co.istad.bmsapi.data.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public PageInfo<BookDto> getBooks(int pageNum, int pageSize) {
        
        Page<Book> bookList = PageHelper.startPage(pageNum, pageSize)
            .doSelectPage(() -> bookRepository.select());

        System.out.println(bookList);
        PageInfo<Book> bookListPageInfo = new PageInfo<>(bookList);
        
        return bookMapper.fromPageInfoModel(bookListPageInfo);
    }
    
}
