package co.istad.bmsapi.api.user.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.istad.bmsapi.api.user.UserServiceImpl;
import co.istad.bmsapi.shared.rest.Rest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserServiceImpl userServiceImpl;
    
    @GetMapping
    ResponseEntity<?> getAllUsers() {
        
        List<UserDto> userDtoList = userServiceImpl.getAllUsers();

        Rest<List<UserDto>> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("Users have been fetched");
        rest.setData(userDtoList);

        return ResponseEntity.ok(rest);
    }

}
