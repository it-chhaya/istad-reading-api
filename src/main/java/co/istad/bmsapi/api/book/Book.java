package co.istad.bmsapi.api.book;

import java.sql.Date;
import java.util.List;

import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    private Long id;
    private String title;
    private String description;
    private String author;
    private Float starRating;
    private Date datePublished;
    private File cover;
    private String pdf;
    private Boolean isEnabled;

    private List<Genre> genres;

}
