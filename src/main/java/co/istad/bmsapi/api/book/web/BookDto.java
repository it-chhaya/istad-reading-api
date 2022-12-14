package co.istad.bmsapi.api.book.web;

import co.istad.bmsapi.api.file.web.FileDto;
import co.istad.bmsapi.api.genre.web.GenreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookDto {

    private Long id;
    private String title;
    private String description;
    private String author;
    private Short starRating;
    private LocalDate datePublished;
    private FileDto cover;
    private String pdf;

    private List<GenreDto> genres;

}
