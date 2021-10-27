package com.pipiolo.springboot.controller.api;

import com.pipiolo.springboot.service.PostService;
import com.pipiolo.springboot.dto.PostResponseDto;
import com.pipiolo.springboot.dto.PostSaveRequestDto;

import com.pipiolo.springboot.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ApiPostController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delte(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
