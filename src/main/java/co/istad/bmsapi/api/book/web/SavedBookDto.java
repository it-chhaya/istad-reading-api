package co.istad.bmsapi.api.book.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import co.istad.bmsapi.shared.annotation.fileexist.ConstraintFileId;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SavedBookDto {

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

}
