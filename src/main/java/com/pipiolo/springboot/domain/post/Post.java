package com.pipiolo.springboot.domain.post;

import com.pipiolo.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @Column
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
