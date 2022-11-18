package co.istad.bmsapi.api.auth;

import co.istad.bmsapi.api.auth.web.AuthDto;
import co.istad.bmsapi.api.auth.web.ChangePasswordDto;
import co.istad.bmsapi.api.auth.web.LogInDto;
import co.istad.bmsapi.api.auth.web.RegisterDto;
import co.istad.bmsapi.api.email.EmailServiceImpl;
import co.istad.bmsapi.api.email.web.EmailDto;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.file.FileServiceImpl;
import co.istad.bmsapi.api.user.User;
import co.istad.bmsapi.api.user.UserMapper;
import co.istad.bmsapi.api.user.web.UserDto;
import co.istad.bmsapi.config.security.CustomUserSecurity;
import co.istad.bmsapi.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final FileServiceImpl fileService;
    private final EmailServiceImpl emailService;

    @Value("${file.uri}")
    private String fileBaseUri;

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity customUserSecurity = (CustomUserSecurity) auth.getPrincipal();

        if (!bCryptPasswordEncoder.matches(changePasswordDto.getCurrentPassword(), customUserSecurity.getPassword())) {
            String reason = "Change password is failed!";
            Throwable cause = new Throwable("Current password is wrong!");
            throw new BadCredentialsException(reason, cause);
        }

        Long userId = customUserSecurity.getUser().getId();

        String encodedPassword = bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword());

        userRepository.updatePasswordWhereId(userId, encodedPassword);

    }

    @Override
    public AuthDto logIn(LogInDto logInDto) {

        System.out.println("Log in = " + logInDto);

        // Get user data
        Authentication authentication = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(logInDto.getUsernameOrEmail(), logInDto.getPassword()));
        CustomUserSecurity customUserSecurity = (CustomUserSecurity) authentication.getPrincipal();

        System.out.println(customUserSecurity.getUser());

        UserDto userDto = userMapper.toUserDto(customUserSecurity.getUser());
        System.out.println(userDto);
        userDto.getProfile().buildNameAndUri(fileBaseUri);

        System.out.println(userDto.getEmail());

        // Setup role for response with authDto
        List<String> roles = new ArrayList<>();
        customUserSecurity.getAuthorities().forEach(role -> roles.add(role.getAuthority()));

        return AuthDto.builder()
                .user(userDto)
                .roles(roles)
                .authHeader(this.buildAuthorizationHeader(logInDto.getUsernameOrEmail(), logInDto.getPassword()))
                .build();
    }


    @Override
    public UserDto register(RegisterDto registerDto) {

        User user = authMapper.fromRegisterDto(registerDto);
        user.setProfile(new File(registerDto.getProfileId()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsEnabled(false);

        userRepository.insert(user);

        registerDto.getRoleIds().forEach(roleId -> {
            userRepository.insertUserRole(user.getId(), roleId);
        });

        UserDto userDto = userMapper.toUserDto(user);
        userDto.setProfile(fileService.getFileByID(registerDto.getProfileId()));

        return userDto;
    }


    private String buildAuthorizationHeader(String usernameOrEmail, String password) {

        // Logic on basic authorization header
        String basicAuthFormat = usernameOrEmail + ":" + password;
        String encoding = Base64.getEncoder().encodeToString(basicAuthFormat.getBytes());

        return String.format("Basic %s", encoding);
    }


    @Override
    public void sendEmailConfirmation(String email) throws MessagingException, UnsupportedEncodingException, ResponseStatusException {

        var random = new Random();
        String code = String.format("%6d", random.nextInt(999999));

        var user = userRepository.selectWhereUsernameOrEmail(email, false).orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
        user.setVerificationCode(code);

        userRepository.updateVerificationCodeWhereId(user.getId(), user.getVerificationCode());

        EmailDto<?> emailDto = EmailDto.builder()
                .receiver(email)
                .subject("Email Verification")
                .templateName("email/email-confirmation")
                .additionalInfo(user)
                .build();

        emailService.sendEmail(emailDto);
    }


    @Override
    public void verifyEmail(String email, String verificationCode) {
        User user = userRepository.selectWhereEmailAndVerificationCode(email, verificationCode)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
        userRepository.updateVerificationCodeWhereId(user.getId(), null);
        userRepository.updateIsEnabledWhereId(user.getId(), true);
    }

}
