package co.istad.bmsapi.api.auth;

import co.istad.bmsapi.api.auth.web.AuthDto;
import co.istad.bmsapi.api.auth.web.ChangePasswordDto;
import co.istad.bmsapi.api.auth.web.LogInDto;
import co.istad.bmsapi.api.auth.web.RegisterDto;
import co.istad.bmsapi.api.user.web.UserDto;

public interface AuthService {

    /**
     * Authenticate user with email or username
     * @param logInDto contains required information for authentication process
     * @return AuthDto
     */
    AuthDto logIn(LogInDto logInDto);



    /**
     * Register new user into database
     * @param registerDto contains required and optional information for register process
     * @return AuthDto
     */
    AuthDto register(RegisterDto registerDto);



    /**
     * Change password of user
     * @param id is the identifier of user
     * @param changePasswordDto contains required credentials
     */
    void changePassword(Long id, ChangePasswordDto changePasswordDto);

}
