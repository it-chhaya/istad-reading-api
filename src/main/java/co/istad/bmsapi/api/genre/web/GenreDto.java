package co.istad.bmsapi.api.genre.web;

import co.istad.bmsapi.api.file.web.FileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class GenreDto {

    private Integer id;
    private String title;
    private String description;
    private FileDto icon;

}
