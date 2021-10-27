package com.pipiolo.springboot.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipiolo.springboot.domain.post.Post;
import com.pipiolo.springboot.domain.post.PostRepository;
import com.pipiolo.springboot.dto.PostRequest;
import com.pipiolo.springboot.dto.PostUpdateRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                //.apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    //@WithMockUser(roles = "USER")
    public void save_posts() throws Exception {

        // given
        String title = "title";
        String content = "content";
        PostRequest requestDto = PostRequest.builder()
                                                                .title(title)
                                                                .content(content)
                                                                .author("author")
                                                                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    //@WithMockUser(roles = "USER")
    public void update_posts() throws Exception {

        // given
        Post savedPost = postRepository.save(Post.builder()
                                                        .title("title")
                                                        .content("content")
                                                        .author("author")
                                                        .build());

        Long updateId = savedPost.getId();
        String expectedTitle   = "title2";
        String expectedContent = "content2";

        PostUpdateRequest requestDto = PostUpdateRequest.builder()
                                                                    .title(expectedTitle)
                                                                    .content(expectedContent)
                                                                    .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostUpdateRequest> requestEntity = new HttpEntity<>(requestDto);

        // when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
