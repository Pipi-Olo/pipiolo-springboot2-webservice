package com.pipiolo.springboot.controller.api;

import com.pipiolo.springboot.dto.APIDataResponse;
import com.pipiolo.springboot.service.PostService;
import com.pipiolo.springboot.dto.post.PostResponse;
import com.pipiolo.springboot.dto.post.PostRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class APIPostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    public APIDataResponse<Void> save(@Valid @RequestBody PostRequest request) {
        postService.save(request);
        return APIDataResponse.empty();
    }

    @GetMapping("/posts/{id}")
    public APIDataResponse<PostResponse> findById(@Positive @PathVariable Long id) {
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
            @Valid @RequestBody PostRequest request
    ) {
        postService.update(id, request);
        return APIDataResponse.empty();
    }

    @DeleteMapping("/posts/{id}")
    public APIDataResponse<Void> delete(@Positive @PathVariable Long id) {
        postService.delete(id);
        return APIDataResponse.empty();
    }

}
