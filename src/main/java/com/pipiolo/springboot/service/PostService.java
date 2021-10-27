package com.pipiolo.springboot.service;

import com.pipiolo.springboot.domain.post.Post;
import com.pipiolo.springboot.domain.post.PostRepository;
import com.pipiolo.springboot.dto.PostListResponseDto;
import com.pipiolo.springboot.dto.PostResponseDto;
import com.pipiolo.springboot.dto.PostSaveRequestDto;

import com.pipiolo.springboot.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find. id = " + id));
        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find. id = " + id));
        postRepository.delete(post);
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find id = " + id));

        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
