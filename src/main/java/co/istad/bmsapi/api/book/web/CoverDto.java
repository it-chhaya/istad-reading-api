package co.istad.bmsapi.api.book.web;

import co.istad.bmsapi.shared.validation.fileid.ConstraintFileId;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoverDto {

    @ConstraintFileId
    private Long coverId;

}
