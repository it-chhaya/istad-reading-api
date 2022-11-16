package co.istad.bmsapi.api.auth.web;

import co.istad.bmsapi.shared.validation.email.ConstraintEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class EmailConfirmationDto {

    @Email
    @NotBlank
    @JsonProperty("email")
    private String value;

}
