package com.pipiolo.springboot.dto;

import com.pipiolo.springboot.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponseDto(Post entity) {
        this.id      = entity.getId();
        this.title   = entity.getTitle();
        this.content = entity.getContent();
        this.author  = entity.getAuthor();
    }
}
