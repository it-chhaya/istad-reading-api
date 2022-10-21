package co.istad.bmsapi.api.genre.web;

import lombok.Data;

@Data
public class PostGenreDto {
    private String title;
    private String description;
    private Integer icon;
}
