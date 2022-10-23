package co.istad.bmsapi.api.genre;

import java.util.List;

import co.istad.bmsapi.api.genre.web.GenreDto;
import co.istad.bmsapi.api.genre.web.PostGenreDto;

public interface GenreService {
    
    List<GenreDto> findAllGenres();

    GenreDto postGenre(PostGenreDto body);


    /**
     * Find genre in db by ID
     * @param id
     * @return
     */
    GenreDto findGenreById(Integer id);



    /**
     * Delete genre from db by ID
     * @param id
     */
    void deleteGenreById(Integer id);

}
