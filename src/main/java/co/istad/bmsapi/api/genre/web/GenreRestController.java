package co.istad.bmsapi.api.genre.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.istad.bmsapi.api.genre.GenreServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
@Slf4j
public class GenreRestController {
    
    private final GenreServiceImpl genreServiceImpl;

    @GetMapping
    public ResponseEntity<?> getGenres() {

        List<GenreDto> genresDto = genreServiceImpl.findAllGenres();

        Rest<List<GenreDto>> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Genre have been fetched");
        rest.setData(genresDto);

        return ResponseEntity.ok(rest);
        
    }


    @PostMapping
    public ResponseEntity<?> postGenre(@Valid @RequestBody PostGenreDto body) {
        
        Rest<GenreDto> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.CREATED.value());
        rest.setMessage("Genre has been inserted");
        rest.setData(genreServiceImpl.postGenre(body));

        return new ResponseEntity<>(rest, HttpStatus.CREATED);

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable Integer id) {
        Rest<GenreDto> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Genre have been fetched");
        rest.setData(genreServiceImpl.findGenreById(id));

        return ResponseEntity.ok(rest);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenreById(@PathVariable Integer id) {

        GenreDto genreDto = genreServiceImpl.deleteGenreById(id);

        var rest = new Rest<GenreDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Genre has been deleted");
        rest.setData(genreDto);

        return ResponseEntity.ok(rest);

    }


    @PutMapping("/{id}")
    ResponseEntity<?> updateGenreById(@PathVariable Integer id, @Valid @RequestBody PostGenreDto postGenreDto) {

        GenreDto genreDto = genreServiceImpl.updateGenreById(id, postGenreDto);

        Rest<?> rest = Rest.ok()
                .setData(genreDto)
                .setMessage("Genre has been updated successfully");

        return ResponseEntity.ok(rest);
    }

}
