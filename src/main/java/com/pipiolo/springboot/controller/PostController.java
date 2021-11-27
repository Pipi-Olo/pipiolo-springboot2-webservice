package com.pipiolo.springboot.controller;

import com.pipiolo.springboot.dto.post.PostResponse;
import com.pipiolo.springboot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping
    public ModelAndView posts() {
        Map<String, Object> map = new HashMap<>();
        List<PostResponse> postList = postService.findAllDesc();
        map.put("posts", postList);

        return new ModelAndView("post/index", map);
    }
}
