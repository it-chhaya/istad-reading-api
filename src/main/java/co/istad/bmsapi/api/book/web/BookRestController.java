package co.istad.bmsapi.api.book.web;

import co.istad.bmsapi.api.book.BookServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookServiceImpl bookService;


    @PutMapping("/{id}/cover")
    ResponseEntity<?> changeCover(@PathVariable("id") Long id, @Valid @RequestBody CoverDto coverDto) {

        String uri = bookService.changeCoverById(id, coverDto);

        var rest = new Rest<String>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Book cover has been changed.");
        rest.setData(uri);

        return ResponseEntity.ok(rest);
    }

    @PutMapping("/{id}/star-rating")
    ResponseEntity<?> rateStar(@PathVariable("id") Long id, @Valid @RequestBody StarRatingDto body) {

        BookDto bookDto = bookService.rateStarById(id, body);

        var rest = new Rest<BookDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Book has been rated.");
        rest.setData(bookDto);

        return ResponseEntity.ok(rest);
    }


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
        rest.setMessage("Book has been fetched.");
        rest.setData(bookService.getBookById(id));

        return ResponseEntity.ok(rest);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBookById(@PathVariable Long id) {

        bookService.deleteBookById(id);

        var rest = new Rest<Long>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Book has been deleted.");
        rest.setData(id);

        return ResponseEntity.ok(rest);
    }


    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody SavedBookDto savedBookDto) {
        return ResponseEntity.ok(this.saveBook(savedBookDto));
    }


    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid @RequestBody SavedBookDto savedBookDto, @PathVariable("id") Long id) {
        savedBookDto.setId(id);
        return ResponseEntity.ok(this.saveBook(savedBookDto));
    }


    private Rest<?> saveBook(SavedBookDto savedBookDto) {

        BookDto bookDto = bookService.save(savedBookDto);

        var rest = new Rest<BookDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Book has been saved.");
        rest.setData(bookDto);

        return rest;
    }

}
