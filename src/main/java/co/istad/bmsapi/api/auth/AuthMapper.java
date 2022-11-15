package co.istad.bmsapi.api.auth;

import co.istad.bmsapi.api.auth.web.AuthDto;
import co.istad.bmsapi.api.auth.web.LogInDto;
import co.istad.bmsapi.api.auth.web.RegisterDto;
import co.istad.bmsapi.api.user.User;
import co.istad.bmsapi.api.user.web.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User fromRegisterDto(RegisterDto registerDto);
    User fromLoginDto(LogInDto logInDto);

    AuthDto toAuthDto(UserDto userDto);

}
