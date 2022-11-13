package co.istad.bmsapi.api.book.web;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import co.istad.bmsapi.shared.constraint.fileid.ConstraintFileId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SavedBookDto {
    
    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String author;

    @NotNull
    private Boolean isPublic;

    @ConstraintFileId
    private Long cover;

    private String pdf;

    //@ConstraintGenreIds
    private List<Integer> genres;

}
