package co.istad.bmsapi.api.genre;

import java.util.List;

import co.istad.bmsapi.api.genre.web.GenreDto;
import co.istad.bmsapi.api.genre.web.PostGenreDto;

public interface GenreService {
    
    List<GenreDto> findAllGenres();

    GenreDto findGenreById(Integer id);

    GenreDto postGenre(PostGenreDto body);

}
