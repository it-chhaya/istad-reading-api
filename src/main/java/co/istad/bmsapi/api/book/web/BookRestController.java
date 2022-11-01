package co.istad.bmsapi.api.book.web;

import co.istad.bmsapi.api.book.Book;
import co.istad.bmsapi.api.book.BookServiceImpl;
import co.istad.bmsapi.api.genre.web.GenreDto;
import co.istad.bmsapi.shared.rest.Rest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookServiceImpl bookService;
    
    @GetMapping
    ResponseEntity<?> getBooks(@RequestBody(required = false) BookFilter bookFilter,
                               @RequestParam(required = false, defaultValue = "1") int pageNum,
                               @RequestParam(required = false, defaultValue = "20") int pageSize) {

        PageInfo<BookDto> bookDtoList = bookService.getAllBooks(bookFilter, pageNum, pageSize);

        Rest<PageInfo<BookDto>> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Books have been fetched");
        rest.setData(bookDtoList);

        return ResponseEntity.ok(rest);

    }


    @GetMapping("/{id}")
    ResponseEntity<?> getBookById(@PathVariable Long id) {

        Rest<BookDto> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Book has been fetched");
        rest.setData(bookService.getBookById(id));

        return ResponseEntity.ok(rest);

    }


//    @PostMapping
//    ResponseEntity<?> postNewBook() {
//
//    }

}
