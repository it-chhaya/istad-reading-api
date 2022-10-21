package co.istad.bmsapi.api.genre;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.istad.bmsapi.api.genre.web.GenreDto;
import co.istad.bmsapi.api.genre.web.PostGenreDto;
import co.istad.bmsapi.data.repository.GenreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> findAllGenres() {

        List<GenreDto> genresDto = new ArrayList<>();
        List<Genre> genres = genreRepository.select();

        genres.forEach(genre -> {
            GenreDto genreDto = new GenreDto();
            genreDto.setTitle(genre.getTitle());
            genreDto.setDescription(genre.getDescription());
            genresDto.add(genreDto);
            //GenreDto genreDto = modelMapper.map(genre, GenreDto.class);
            //genresDto.add(genreDto);
        });

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

        Genre genre = new Genre();
        genre.setTitle(body.getTitle());
        genre.setDescription(body.getDescription());
        genre.setIcon(body.getIcon());
        genre.setIsEnabled(true);
        // Genre genre = modelMapper.map(body, Genre.class);
        // genre.setIsEnabled(true);

        genreRepository.insert(genre);

        Genre genreById = genreRepository.selectById(genre.getId());

        GenreDto genreDto = new GenreDto();
        genreDto.setTitle(genreById.getTitle());
        genreDto.setDescription(genreById.getDescription());
        //GenreDto genreDto = modelMapper.map(genreById, GenreDto.class);

        return genreDto;
    }

}
