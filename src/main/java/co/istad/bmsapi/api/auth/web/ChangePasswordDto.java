package co.istad.bmsapi.api.auth.web;

import co.istad.bmsapi.shared.validation.password.Password;
import co.istad.bmsapi.shared.validation.password.PasswordMatch;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatch(password = "templates/password", confirmedPassword = "confirmedPassword")
public class ChangePasswordDto {

    @Password
    @NotBlank
    private String password;

    @Password
    @NotBlank
    private String confirmedPassword;

}
