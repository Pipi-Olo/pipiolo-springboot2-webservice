package com.pipiolo.springboot.service;

import com.pipiolo.springboot.domain.user.User;
import com.pipiolo.springboot.domain.user.UserRepository;
import com.pipiolo.springboot.dto.user.LoginRequest;
import com.pipiolo.springboot.dto.user.SignupRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static com.pipiolo.springboot.domain.user.Role.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String LOGIN_SESSION_KEY = "USER_ID";

    @Transactional
    public void signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException(request.getEmail() + "은 이미 있는 Email 입니다.");
        }

        userRepository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(USER)
                .build());
    }

    @Transactional
    public void signupAdmin(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException(request.getEmail() + "은 이미 있는 Email 입니다.");
        }

        userRepository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ADMIN)
                .build());
    }

    @Transactional
    public void login(LoginRequest request, HttpSession session) { // String email, String password) {
        Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if (userId != null) {
            log.info("Already Login!");
            return;
        }

        User user = userRepository.findByEmail(request.getEmail())
                .map(u -> u.isPasswordMatch(passwordEncoder.encode(request.getPassword())) ? u : null)
                .orElseThrow(() -> new IllegalArgumentException(
                        request.getEmail() + " or " + request.getPassword() + " not match."));

        session.setAttribute(LOGIN_SESSION_KEY, user.getId());
    }

    public void logout(HttpSession session) {
        session.removeAttribute(LOGIN_SESSION_KEY);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
