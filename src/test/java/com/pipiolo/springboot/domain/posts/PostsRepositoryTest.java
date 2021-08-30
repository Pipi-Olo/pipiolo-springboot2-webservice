package com.pipiolo.springboot.domain.posts;

import com.pipiolo.springboot.domain.posts.Posts;
import com.pipiolo.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void load_post() {

        // given
        String title   = "Test Title";
        String content = "Test Content";

        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("pipiolo@gmail.com")
                                    .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo("pipiolo@gmail.com");
    }

    @Test
    public void save_baseTimeEntity() {

        // given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate = " + posts.getCreatedTime() + " modifiedDate = " + posts.getModifiedTime());

        assertThat(posts.getCreatedTime()).isAfter(now);
        assertThat(posts.getModifiedTime()).isAfter(now);
    }
}
