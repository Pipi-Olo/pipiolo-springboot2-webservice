package com.pipiolo.springboot.config;

import com.pipiolo.springboot.dto.user.SignupRequest;
import com.pipiolo.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InitializeDefaultConfig {

    private final UserService userService;

    @Bean
    public void initializeUser() {
        SignupRequest request = new SignupRequest("user", "user", "user");
        userService.signup(request);
    }

    @Bean
    public void initializeAdmin() {
        SignupRequest request = new SignupRequest("admin", "admin", "admin");
        userService.signupAdmin(request);
    }
}
