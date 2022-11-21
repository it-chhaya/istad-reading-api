package co.istad.bmsapi.api.book.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import co.istad.bmsapi.shared.validation.fileid.ConstraintFileId;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SavedBookDto {

    @JsonIgnore
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String author;

    @ConstraintFileId
    private Long fileId;

    private String pdf;

    @NotNull
    private Boolean isPublished;

    // @ConstraintGenreId
    @NotEmpty(message = "You must specify the genre id.")
    private List<Integer> genreIds;

}
