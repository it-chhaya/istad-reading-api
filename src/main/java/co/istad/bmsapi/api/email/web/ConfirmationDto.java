package co.istad.bmsapi.api.email.web;

import co.istad.bmsapi.api.user.web.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ConfirmationDto {

    private UserDto userDto;

}
