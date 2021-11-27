package com.pipiolo.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    public String post() {
        return "post/index";
    }
}
