package co.istad.bmsapi.shared.validation.email;

import co.istad.bmsapi.api.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ConstraintEmailValidator implements ConstraintValidator<ConstraintEmail, String> {

    private final UserServiceImpl userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.checkUserEmail(email);
    }

}
