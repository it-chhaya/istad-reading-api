package co.istad.bmsapi.api.auth.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class LogInDto {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

}
