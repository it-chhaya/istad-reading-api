package co.istad.bmsapi.api.book.web;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarRatingDto {

    @NotNull
    @Min(0)
    @Max(5)
    private Short starRating;

}
