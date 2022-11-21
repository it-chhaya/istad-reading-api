package co.istad.bmsapi.api.auth.web;

import co.istad.bmsapi.api.user.web.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AuthDto {

    private UserDto user;

    private List<String> roles;

    private String authHeader;

}
