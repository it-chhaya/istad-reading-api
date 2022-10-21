package co.istad.bmsapi.api.genre;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.istad.bmsapi.api.genre.web.GenreDto;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    
    public Genre toModel(GenreDto dto);

    //@Mapping(source = "title", target = "title")
    public GenreDto toDto(Genre genre);

}
