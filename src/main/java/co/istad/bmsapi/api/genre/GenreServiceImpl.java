package co.istad.bmsapi.api.genre;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.istad.bmsapi.api.genre.web.GenreDto;
import co.istad.bmsapi.api.genre.web.PostGenreDto;
import co.istad.bmsapi.data.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> findAllGenres() {

        List<Genre> genres = genreRepository.select();

        List<GenreDto> genresDto = genreMapper.toListDto(genres);

        return genresDto;
        
    }
    

    @Override
    public GenreDto postGenre(PostGenreDto body) {

        // Convert Dto to Model
        Genre genre = genreMapper.toPostModel(body);
        genre.setIsEnabled(true);

        log.info("Genre Before Insert = {}", genre);

        genreRepository.insert(genre);

        log.info("Genre After Insert = {}", genre);

        return genreMapper.toDto(genre);
        
    }


    @Override
    public GenreDto findGenreById(Integer id) {
        Genre genre = genreRepository.selectWhereId(id);
        GenreDto genreDto = genreMapper.toDto(genre);
        return genreDto;
    }


    @Override
    public void deleteGenreById(Integer id) {

        boolean isFound = genreRepository.existsById(id);

        if (!isFound) {
            String reason = "Genre with ID = " + id + " is not found in DB";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        genreRepository.deleteWhereId(id);
    }


    @Override
    public boolean existsById(Integer id) {
        return genreRepository.existsById(id);
    }

}
