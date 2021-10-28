package com.pipiolo.springboot.domain.post;

import com.pipiolo.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 500, nullable = false)
    private String title;

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Setter
    private String author;

    @Builder
    public Post(String title, String content, String author) {
        this.title   = title;
        this.content = content;
        this.author  = author;
    }

    public void update(String title, String content) {
        this.title   = title;
        this.content = content;
    }
}
