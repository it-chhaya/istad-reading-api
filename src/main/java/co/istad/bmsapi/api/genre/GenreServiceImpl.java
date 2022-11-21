package co.istad.bmsapi.api.genre;

import java.util.ArrayList;
import java.util.List;

import co.istad.bmsapi.api.file.File;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.uri}")
    private String fileBaseUri;

    @Override
    public List<GenreDto> findAllGenres() {

        List<Genre> genres = genreRepository.select();

        List<GenreDto> genresDto = genreMapper.toListDto(genres);

        genresDto.forEach(genreDto -> genreDto.getIcon().buildNameAndUri(fileBaseUri));

        return genresDto;
    }
    

    @Override
    public GenreDto postGenre(PostGenreDto body) {

        // Convert Dto to Model
        Genre genre = genreMapper.toPostModel(body);
        genre.setIsEnabled(true);
        genre.setIcon(new File(body.getIconId()));

        genreRepository.insert(genre);

        return this.findGenreById(genre.getId());
    }


    @Override
    public GenreDto findGenreById(Integer id) {

        Genre genre = genreRepository.selectWhereId(id).orElseThrow(() -> {
            String reason = "Cannot find genre resource!";
            Throwable cause = new Throwable("Genre with ID = " + id + " is not found in DB!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, cause);
        });

        GenreDto genreDto = genreMapper.toDto(genre);
        genreDto.getIcon().buildNameAndUri(fileBaseUri);

        return genreDto;
    }


    @Override
    public GenreDto deleteGenreById(Integer id) {

        Genre genre = genreRepository.selectWhereId(id).orElseThrow(() -> {
            String reason = "Cannot delete genre resource!";
            Throwable cause = new Throwable("Genre with ID = " + id + " is not found in DB!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, cause);
        });

        genreRepository.deleteWhereId(id);

        GenreDto genreDto = genreMapper.toDto(genre);
        genreDto.getIcon().buildNameAndUri(fileBaseUri);

        return genreDto;
    }


    @Override
    public GenreDto updateGenreById(Integer id, PostGenreDto postGenreDto) {

        boolean isFound = genreRepository.existsById(id);

        if (!isFound) {
            String reason = "Cannot update genre resource!";
            Throwable cause = new Throwable("Genre with ID = " + id + " is not found in DB!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason, cause);
        }

        Genre genre = genreMapper.toPostModel(postGenreDto);
        genre.setId(id);
        genre.setIcon(new File(postGenreDto.getIconId()));

        genreRepository.updateWhereId(genre);

        return this.findGenreById(id);
    }


    @Override
    public boolean existsById(Integer id) {
        return genreRepository.existsById(id);
    }

}
