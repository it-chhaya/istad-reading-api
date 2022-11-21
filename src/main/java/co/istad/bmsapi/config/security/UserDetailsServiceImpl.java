package co.istad.bmsapi.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.istad.bmsapi.api.user.User;
import co.istad.bmsapi.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.selectWhereUsernameOrEmail(usernameOrEmail, true).orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
        log.info("User = {}", user);

        CustomUserSecurity userSecurity = new CustomUserSecurity();
        userSecurity.setUser(user);

        return userSecurity;
    }
    
}
