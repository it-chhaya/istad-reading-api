package co.istad.bmsapi.api.genre.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class PostGenreDto {

    @NotBlank(message = "Title cannot be nulled")
    private String title;

    @NotBlank(message = "Description cannot be nulled")
    private String description;

    @Positive
    private Integer icon;

}
