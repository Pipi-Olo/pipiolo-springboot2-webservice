package com.pipiolo.springboot.controller;

import com.pipiolo.springboot.dto.user.LoginRequest;
import com.pipiolo.springboot.dto.user.SignupRequest;
import com.pipiolo.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @Valid @ModelAttribute SignupRequest request
    ) {
        userService.signup(request);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute LoginRequest request,
            HttpSession session
    ) {
        userService.login(request, session);
        return "redirect:index";
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        userService.logout(session);
    }
}
