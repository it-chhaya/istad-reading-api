package co.istad.bmsapi.api.book;

import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.genre.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    private List<Genre> genres;

}
