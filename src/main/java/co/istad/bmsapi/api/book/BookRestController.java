package co.istad.bmsapi.api.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookRestController {
    
    @GetMapping
    public Book getBooks() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("My Book");
        book.setDescription("My Book Desc");
        return book;
    }

}
