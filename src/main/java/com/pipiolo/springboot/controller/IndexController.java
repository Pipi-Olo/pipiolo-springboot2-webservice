package com.pipiolo.springboot.controller;

import com.pipiolo.springboot.config.auth.LoginUser;
import com.pipiolo.springboot.config.auth.dto.SessionUser;
import com.pipiolo.springboot.service.PostService;
import com.pipiolo.springboot.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession  httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}

