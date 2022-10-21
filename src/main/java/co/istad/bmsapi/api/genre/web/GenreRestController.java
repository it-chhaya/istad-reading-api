package co.istad.bmsapi.api.genre.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.istad.bmsapi.api.genre.Genre;
import co.istad.bmsapi.api.genre.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
@Slf4j
public class GenreRestController {
    
    private final GenreServiceImpl genreServiceImpl;

    @GetMapping
    public List<GenreDto> getGenre() {
        return genreServiceImpl.findAllGenres();
    }


    @GetMapping("/{id}")
    public GenreDto getGenreById(@PathVariable Integer id) {
        return genreServiceImpl.findGenreById(id);
    }


    @PostMapping
    public GenreDto postGenre(@RequestBody PostGenreDto body) {
        return genreServiceImpl.postGenre(body);
    }

}
