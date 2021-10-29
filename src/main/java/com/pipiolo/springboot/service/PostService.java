package com.pipiolo.springboot.service;

import com.pipiolo.springboot.domain.post.Post;
import com.pipiolo.springboot.domain.post.PostRepository;
import com.pipiolo.springboot.dto.*;

import com.pipiolo.springboot.exception.APIException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.pipiolo.springboot.exception.ErrorCode.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void save(PostRequest request) {
        postRepository.save(request.toEntity());
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new APIException(NOT_FOUND));
        return new PostResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new APIException(NOT_FOUND));
        post.update(request.getTitle(), request.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new APIException(NOT_FOUND));
        postRepository.delete(post);
    }
}
