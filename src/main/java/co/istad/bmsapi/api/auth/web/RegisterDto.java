package co.istad.bmsapi.api.auth.web;

import co.istad.bmsapi.shared.validation.email.ConstraintEmail;
import co.istad.bmsapi.shared.validation.fileid.ConstraintFileId;
import co.istad.bmsapi.shared.validation.password.Password;
import co.istad.bmsapi.shared.validation.password.PasswordMatch;
import co.istad.bmsapi.shared.validation.username.ConstraintUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@PasswordMatch(password = "password", confirmedPassword = "confirmedPassword")
public class RegisterDto {

    @ConstraintUsername
    @NotBlank
    private String username;

    @ConstraintEmail
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String familyName;

    @NotBlank
    private String givenName;

    @NotBlank
    private String phoneNumber;

    @ConstraintFileId(message = "Profile ID does not exist!")
    private Long profileId;

    private String biography;

    @Password
    @NotBlank
    private String password;

    @Password
    @NotBlank
    private String confirmedPassword;

    private List<Integer> roleIds;

}
