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
@PasswordMatch(password = "newPassword", confirmedPassword = "confirmedPassword")
public class ChangePasswordDto {

    @Password
    @NotBlank
    private String currentPassword;

    @Password
    @NotBlank
    private String newPassword;

    @Password
    @NotBlank
    private String confirmedPassword;

}
