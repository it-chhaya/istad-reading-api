package co.istad.bmsapi.api.book.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDate;

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
    private Integer cover;
    private String pdf;

}
