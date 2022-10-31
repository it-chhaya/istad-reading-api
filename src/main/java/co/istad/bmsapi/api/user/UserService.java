package co.istad.bmsapi.api.user;

import java.util.List;

import co.istad.bmsapi.api.user.web.UserDto;

public interface UserService {
    
    List<UserDto> getAllUsers();

}
