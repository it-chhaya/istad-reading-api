package co.istad.bmsapi.api.genre;

import java.util.List;

import co.istad.bmsapi.api.genre.web.GenreDto;
import co.istad.bmsapi.api.genre.web.PostGenreDto;

public interface GenreService {


    /**
     * Retrieving all records of genre from db
     * @return List<GenreDto>
     */
    List<GenreDto> findAllGenres();


    /**
     * Insert genre into db
     * @param body is the request body for inserting the record
     * @return GenreDto
     */
    GenreDto postGenre(PostGenreDto body);


    /**
     * Find genre in db by ID
     * @param id is the primary key of record
     * @return GenreDto
     */
    GenreDto findGenreById(Integer id);



    /**
     * Delete genre from db by ID
     * @param id is the primary key of record
     * @return GenreDto
     */
    GenreDto deleteGenreById(Integer id);


    /**
     * Update genre by ID
     * @param id is the primary key of record
     * @param postGenreDto is the request body for updating the record
     * @return GenreDto
     */
    GenreDto updateGenreById(Integer id, PostGenreDto postGenreDto);



    /**
     * Check ID of genre exist or not
     * @param id
     * @return
     */
    boolean existsById(Integer id);

}
