package com.pipiolo.springboot.controller.api;

import com.pipiolo.springboot.dto.APIDataResponse;
import com.pipiolo.springboot.service.PostService;
import com.pipiolo.springboot.dto.PostResponse;
import com.pipiolo.springboot.dto.PostRequest;

import com.pipiolo.springboot.dto.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class APIPostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    public APIDataResponse<Void> save(@RequestBody PostRequest request) {
        postService.save(request);
        return APIDataResponse.empty();
    }

    @GetMapping("/posts/{id}")
    public APIDataResponse<PostResponse> findById(@PathVariable Long id) {
        PostResponse response = postService.findById(id);
        return APIDataResponse.of(response);
    }

    @GetMapping("/posts")
    public APIDataResponse<List<PostResponse>> findAll() {
        List<PostResponse> responseList = postService.findAllDesc();
        return APIDataResponse.of(responseList);
    }

    @PutMapping("/posts/{id}")
    public APIDataResponse<Void> update(
            @PathVariable Long id,
            @RequestBody PostRequest request
    ) {
        postService.update(id, request);
        return APIDataResponse.empty();
    }

    @DeleteMapping("/posts/{id}")
    public APIDataResponse<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return APIDataResponse.empty();
    }


}
