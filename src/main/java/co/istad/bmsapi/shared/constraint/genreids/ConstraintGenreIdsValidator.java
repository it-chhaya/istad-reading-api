package co.istad.bmsapi.shared.constraint.genreids;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import co.istad.bmsapi.api.genre.GenreServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstraintGenreIdsValidator implements ConstraintValidator<ConstraintGenreIds, List<Integer>> {

    private final GenreServiceImpl genreServiceImpl;

    @Override
    public boolean isValid(List<Integer> genreIds, ConstraintValidatorContext context) {
        
        if (genreIds.size() > 0) {
            for (Integer genreId : genreIds) {
                if (!genreServiceImpl.existsById(genreId))
                    return false;
            }
            return true;
        }
            
        return false;
    }
    
}
