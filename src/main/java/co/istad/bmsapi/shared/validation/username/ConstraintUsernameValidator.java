package co.istad.bmsapi.shared.validation.username;

import co.istad.bmsapi.api.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ConstraintUsernameValidator implements ConstraintValidator<ConstraintUsername, String> {

    private final UserServiceImpl userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userService.checkUsername(username);
    }

}
