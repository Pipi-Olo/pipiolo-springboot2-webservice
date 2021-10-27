package com.pipiolo.springboot.domain.post;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

//    @Autowired
//    PostRepository postRepository;
//
//    @After
//    public void cleanup() {
//        postRepository.deleteAll();
//    }
//
//    @Test
//    public void road_post() {
//
//        // given
//        String title   = "Test Title";
//        String content = "Test Content";
//
//        postRepository.save(Post.builder()
//                                    .title(title)
//                                    .content(content)
//                                    .author("pipiolo@gmail.com")
//                                    .build());
//
//        // when
//        List<Post> postList = postRepository.findAll();
//
//        // then
//        Post post = postList.get(0);
//        assertThat(post.getTitle()).isEqualTo(title);
//        assertThat(post.getContent()).isEqualTo(content);
//        assertThat(post.getAuthor()).isEqualTo("pipiolo@gmail.com");
//    }
//
//    @Test
//    public void save_baseTimeEntity() {
//
//        // given
//        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
//        postRepository.save(Post.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .build());
//
//        // when
//        List<Post> postList = postRepository.findAll();
//
//        // then
//        Post post = postList.get(0);
//
//        System.out.println(">>>>>>>>> createDate = " + post.getCreatedDate() + " modifiedDate = " + post.getModifiedDate());
//
//        assertThat(post.getCreatedDate()).isAfter(now);
//        assertThat(post.getModifiedDate()).isAfter(now);
//    }
}
