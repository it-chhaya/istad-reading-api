package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.file.File;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {

    private Long id;
    private String title;
    private String description;
    private String author;
    private Short starRating;
    private LocalDate datePublished;
    private File cover;
    private String pdf;
    private Boolean isEnabled;

}
