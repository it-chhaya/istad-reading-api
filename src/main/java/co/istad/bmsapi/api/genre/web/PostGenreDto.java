package co.istad.bmsapi.api.genre.web;

import javax.validation.constraints.NotBlank;

import co.istad.bmsapi.shared.validation.fileid.ConstraintFileId;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PostGenreDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @ConstraintFileId
    private Long iconId;

}
