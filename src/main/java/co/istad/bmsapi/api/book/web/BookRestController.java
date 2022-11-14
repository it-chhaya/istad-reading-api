package co.istad.bmsapi.api.book.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import co.istad.bmsapi.api.book.BookServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody @Valid SavedBookDto body) {
        
        BookDto bookDto = bookServiceImpl.postBook(body);

        var rest = new Rest<BookDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Books have been saved");
        rest.setData(bookDto);

        return ResponseEntity.ok(rest);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> putBook(@PathVariable("id") Long id, @RequestBody @Valid SavedBookDto body) {
        
        BookDto bookDto = bookServiceImpl.putBook(id, body);

        var rest = new Rest<BookDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Books have been saved");
        rest.setData(bookDto);

        return ResponseEntity.ok(rest);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id) {
        
        BookDto bookDto = bookServiceImpl.getBookById(id);

        var rest = new Rest<BookDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Books have been saved");
        rest.setData(bookDto);

        return ResponseEntity.ok(rest);
    }


    @GetMapping
    public ResponseEntity<?> getBooks(
        @RequestParam(required = false, defaultValue = "1") int pageNum,
        @RequestParam(required = false, defaultValue = "20") int pageSize) {

        var books = bookServiceImpl.getBooks(pageNum, pageSize);
        
        var rest = new Rest<PageInfo<BookDto>>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Books have been fetched");
        rest.setData(books);

        return ResponseEntity.ok(rest);
    }

}
