package com.pipiolo.springboot.service;

import com.pipiolo.springboot.domain.posts.Posts;
import com.pipiolo.springboot.domain.posts.PostsRepository;
import com.pipiolo.springboot.web.dto.PostsResponseDto;
import com.pipiolo.springboot.web.dto.PostsSaveRequestDto;

import com.pipiolo.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find id = " + id));

        return new PostsResponseDto(entity);
    }
}
