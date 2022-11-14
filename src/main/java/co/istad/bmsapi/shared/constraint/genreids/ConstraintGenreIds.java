package co.istad.bmsapi.shared.constraint.genreids;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ConstraintGenreIdsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstraintGenreIds {
    
    String message() default "Genre ID does not exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
