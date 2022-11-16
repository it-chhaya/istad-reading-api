package co.istad.bmsapi.api.auth.web;

import co.istad.bmsapi.api.auth.AuthServiceImpl;
import co.istad.bmsapi.config.security.UserDetailsServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import co.istad.bmsapi.utils.DateTimeUtils;
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
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

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

        var rest = new HashMap<String, Object>();
        rest.put("status", true);
        rest.put("code", HttpStatus.OK.value());
        rest.put("message", "Password has been changed successfully.");
        rest.put("timestamp", DateTimeUtils.getTS());

        return ResponseEntity.ok(rest);
    }


    @PostMapping("send-email-confirmation")
    ResponseEntity<?> sendEmailConfirmation(@RequestBody EmailConfirmationDto emailConfirmationDto) throws MessagingException, UnsupportedEncodingException, ResponseStatusException {

        authService.sendEmailConfirmation(emailConfirmationDto.getValue());

        var rest = new HashMap<String, Object>();
        rest.put("status", true);
        rest.put("code", HttpStatus.OK.value());
        rest.put("message", "Please check and confirm your email.");
        rest.put("timestamp", DateTimeUtils.getTS());

        return ResponseEntity.ok(rest);
    }



    @GetMapping("verify-email")
    ResponseEntity<?> verifyEmail(@RequestParam("email") String email,
                                  @RequestParam("verificationCode") String verificationCode) {

        System.out.println("email = " + email);
        System.out.println("verificationCode = " + verificationCode);

        authService.verifyEmail(email, verificationCode);

        var rest = new HashMap<String, Object>();
        rest.put("status", true);
        rest.put("code", HttpStatus.OK.value());
        rest.put("message", "Email has been verified successfully.");
        rest.put("timestamp", DateTimeUtils.getTS());

        return ResponseEntity.ok(rest);
    }


}
