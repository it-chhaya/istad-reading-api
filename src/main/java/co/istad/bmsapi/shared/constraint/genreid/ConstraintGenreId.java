package co.istad.bmsapi.shared.constraint.genreid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConstraintGenreIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstraintGenreId {

    String message() default "Genre ID does not exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
