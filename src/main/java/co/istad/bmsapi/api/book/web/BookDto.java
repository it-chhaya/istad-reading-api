package co.istad.bmsapi.api.book.web;

import java.sql.Date;
import java.util.List;

import co.istad.bmsapi.api.file.web.FileDto;
import co.istad.bmsapi.api.genre.web.GenreDto;
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
public class BookDto {
    
    private Long id;
    private String title;
    private String description;
    private String author;
    private Float starRating;
    private Date datePublished;
    private FileDto cover;
    private String pdf;
    private Boolean isEnabled;

    private List<GenreDto> genres;

}
