package co.istad.bmsapi.api.user;

import java.util.List;

import org.mapstruct.Mapper;

import co.istad.bmsapi.api.user.web.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    List<UserDto> toListDto(List<User> users);

    UserDto toUserDto(User user);

    User toUserModel(UserDto userDto);

}
