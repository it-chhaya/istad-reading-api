package co.istad.bmsapi.api.genre;

import java.util.List;

import org.mapstruct.Mapper;

import co.istad.bmsapi.api.genre.web.GenreDto;
import co.istad.bmsapi.api.genre.web.PostGenreDto;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    
    Genre toModel(GenreDto dto);

    GenreDto toDto(Genre genre);

    Genre toPostModel(PostGenreDto postGenreDto);

    PostGenreDto toPostGenreDto(Genre genre);

    List<GenreDto> toListDto(List<Genre> genres);

}
