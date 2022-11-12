package co.istad.bmsapi.api.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookGenre {

    private Long id;
    private Long bookId;
    private Integer genreId;

}
