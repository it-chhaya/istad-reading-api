package co.istad.bmsapi.api.auth.web;

import co.istad.bmsapi.api.auth.AuthServiceImpl;
import co.istad.bmsapi.config.security.UserDetailsServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthServiceImpl authService;


    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {

        AuthDto authDto = authService.register(registerDto);

        Rest<Object> rest = Rest.ok()
                .setData(authDto)
                .setMessage("You have registered successfully.");

        return ResponseEntity.ok(rest);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody LogInDto logInDto) {

        AuthDto authDto = authService.logIn(logInDto);

        var rest = new Rest<AuthDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("You have logged in successfully.");
        rest.setData(authDto);

        return ResponseEntity.ok(rest);
    }


    @PutMapping("/{id}/change-password")
    ResponseEntity<?> changePassword(@PathVariable("id") Long id, @Valid @RequestBody ChangePasswordDto changePasswordDto) {

        authService.changePassword(id, changePasswordDto);

        Rest<Object> rest = Rest.ok()
                .setData("Password has been changed successfully.")
                .setMessage("Operation is successfully.");

        return ResponseEntity.ok(rest);
    }


}
