package co.istad.bmsapi.api.auth;

import co.istad.bmsapi.api.auth.web.AuthDto;
import co.istad.bmsapi.api.auth.web.ChangePasswordDto;
import co.istad.bmsapi.api.auth.web.LogInDto;
import co.istad.bmsapi.api.auth.web.RegisterDto;
import co.istad.bmsapi.api.user.web.UserDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

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
     * @return UserDto
     */
    UserDto register(RegisterDto registerDto);



    /**
     * Change password of user
     * @param changePasswordDto contains required credentials
     */
    void changePassword(ChangePasswordDto changePasswordDto);


    void sendEmailConfirmation(String email) throws MessagingException, UnsupportedEncodingException;


    void verifyEmail(String email, String verificationCode);

}
