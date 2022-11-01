package co.istad.bmsapi.api.book.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookFilter {

    private String title;
    private String author;
    private Short starRating;

}
