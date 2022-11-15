package co.istad.bmsapi.api.user;

import java.util.List;

import org.springframework.stereotype.Service;

import co.istad.bmsapi.api.user.web.UserDto;
import co.istad.bmsapi.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.select();
        return userMapper.toListDto(users);
    }

    @Override
    public boolean checkUserEmail(String email) {
        return userRepository.existsWhereEmail(email);
    }

    @Override
    public boolean checkUsername(String username) {
        return userRepository.existsWhereUsername(username);
    }

}
