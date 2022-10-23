package co.istad.bmsapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build());

        manager.createUser(User.withUsername("author")
                .password(passwordEncoder().encode("author"))
                .roles("AUTHOR")
                .build());

        manager.createUser(User.withUsername("subscriber")
                .password(passwordEncoder().encode("123"))
                .roles("SUBSCRIBER")
                .build());

        return manager;
    }

    // @Bean
    // public AuthenticationManager authManager(HttpSecurity http,
    // UserDetailsService userDetailsService) throws Exception {
    // return http.getSharedObject(AuthenticationManagerBuilder.class)
    // .userDetailsService(userDetailsService)
    // .passwordEncoder(passwordEncoder())
    // .and()
    // .build();
    // }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Start configuring

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/genres/**").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "AUTHOR")
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

}
