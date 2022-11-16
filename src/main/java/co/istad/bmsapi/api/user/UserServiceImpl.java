package co.istad.bmsapi.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.istad.bmsapi.api.user.web.UserDto;
import co.istad.bmsapi.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${file.uri}")
    private String fileBaseUri;


    @Override
    public UserDto getUserByUsernameOrEmail(String usernameOrEmail) {

        User user = userRepository.selectWhereUsernameOrEmail(usernameOrEmail, true).orElseThrow(() ->
                new UsernameNotFoundException("User is not found!"));

        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);

        return userDto;
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.select();
        return userMapper.toListDto(users);
    }


    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.selectWhereId(id).orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);

        return userDto;
    }



    @Override
    public boolean checkUserEmail(String email) {
        return userRepository.existsWhereEmail(email);
    }



    @Override
    public boolean checkUsername(String username) {
        return userRepository.existsWhereUsername(username);
    }



    @Override
    public UserDto enableAndDisableUser(Long id, Boolean isEnabled) {

        User user = userRepository.selectWhereId(id).orElseThrow(() -> new UsernameNotFoundException("User is not found!"));

        userRepository.updateIsEnabledWhereId(id, isEnabled);

        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);

        return userDto;
    }


    @Override
    public UserDto deleteUserById(Long id) {

        User user = userRepository.selectWhereId(id).orElseThrow(() -> new UsernameNotFoundException("User is not found!"));

        userRepository.deleteWhereId(id);

        UserDto userDto = userMapper.toUserDto(user);
        userDto.getProfile().buildNameAndUri(fileBaseUri);

        return userDto;
    }

}
