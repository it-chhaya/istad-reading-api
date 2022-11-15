package co.istad.bmsapi.api.auth;

import co.istad.bmsapi.api.auth.web.AuthDto;
import co.istad.bmsapi.api.auth.web.LogInDto;
import co.istad.bmsapi.api.auth.web.RegisterDto;
import co.istad.bmsapi.api.file.File;
import co.istad.bmsapi.api.user.Role;
import co.istad.bmsapi.api.user.User;
import co.istad.bmsapi.api.user.UserMapper;
import co.istad.bmsapi.api.user.web.UserDto;
import co.istad.bmsapi.config.security.CustomUserSecurity;
import co.istad.bmsapi.config.security.UserDetailsServiceImpl;
import co.istad.bmsapi.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Value("${file.uri}")
    private String fileBaseUri;

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final AuthMapper authMapper;

    @Override
    public AuthDto logIn(LogInDto logInDto) {

        // Get user data
        Authentication authentication = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(logInDto.getUsernameOrEmail(), logInDto.getPassword()));
        CustomUserSecurity customUserSecurity = (CustomUserSecurity) authentication.getPrincipal();
        UserDto userDto = userMapper.toUserDto(customUserSecurity.getUser());
        userDto.getProfile().buildNameAndUri(fileBaseUri);

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
    public AuthDto register(RegisterDto registerDto) {

        User user = authMapper.fromRegisterDto(registerDto);
        user.setProfile(new File(registerDto.getProfileId()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsEnabled(true);

        userRepository.insert(user);

        registerDto.getRoleIds().forEach(roleId -> {
            userRepository.insertUserRole(user.getId(), roleId);
        });

        LogInDto logInDto = LogInDto.builder()
                .usernameOrEmail(registerDto.getEmail())
                .password(registerDto.getPassword())
                .build();

        return this.logIn(logInDto);
    }


    private String buildAuthorizationHeader(String usernameOrEmail, String password) {

        // Logic on basic authorization header
        String basicAuthFormat = usernameOrEmail + ":" + password;
        String encoding = Base64.getEncoder().encodeToString(basicAuthFormat.getBytes());

        return String.format("Basic %s", encoding);
    }

}
