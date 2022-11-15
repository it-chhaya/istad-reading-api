package co.istad.bmsapi.shared.validation.genreid;

import co.istad.bmsapi.data.repository.GenreRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class ConstraintGenreIdValidator implements ConstraintValidator<ConstraintGenreId, List<Integer>> {

    private final GenreRepository genreRepository;

    @Override
    public boolean isValid(List<Integer> ids, ConstraintValidatorContext context) {
        return true;
    }

}
