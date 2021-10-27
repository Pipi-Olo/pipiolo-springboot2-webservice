package com.pipiolo.springboot.controller.api;

import com.pipiolo.springboot.service.PostService;
import com.pipiolo.springboot.dto.PostResponse;
import com.pipiolo.springboot.dto.PostRequest;

import com.pipiolo.springboot.dto.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class APIPostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Long save(@RequestBody PostRequest requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequest requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

    @GetMapping("/posts/{id}")
    public PostResponse findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
