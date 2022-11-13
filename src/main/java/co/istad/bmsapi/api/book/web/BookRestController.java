package co.istad.bmsapi.api.book.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        return ResponseEntity.ok(null);
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
