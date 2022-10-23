package co.istad.bmsapi.api.genre;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
    public GenreDto findGenreById(Integer id) {
        
        Genre genre = genreRepository.selectById(id);
        System.out.println(genre);

        GenreDto genreDto = genreMapper.toDto(genre);
        System.out.println(genreDto);
        return genreDto;
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

}
