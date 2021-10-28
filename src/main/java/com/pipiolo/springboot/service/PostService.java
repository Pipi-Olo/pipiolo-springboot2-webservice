package com.pipiolo.springboot.service;

import com.pipiolo.springboot.domain.post.Post;
import com.pipiolo.springboot.domain.post.PostRepository;
import com.pipiolo.springboot.dto.PostListResponse;
import com.pipiolo.springboot.dto.PostResponse;
import com.pipiolo.springboot.dto.PostRequest;

import com.pipiolo.springboot.dto.PostUpdateRequest;
import com.pipiolo.springboot.exception.APIException;
import com.pipiolo.springboot.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.pipiolo.springboot.exception.ErrorCode.BAD_REQUEST;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostRequest request) {
        return postRepository.save(request.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new APIException(BAD_REQUEST));
        return new PostResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can not find. id = " + id));
        post.update(request.getTitle(), request.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can not find. id = " + id));
        postRepository.delete(post);
    }
}
