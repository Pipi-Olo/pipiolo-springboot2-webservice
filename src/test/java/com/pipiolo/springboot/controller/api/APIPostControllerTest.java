package com.pipiolo.springboot.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipiolo.springboot.dto.PostRequest;
import com.pipiolo.springboot.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("API 컨트롤러 - Post")
@WebMvcTest(APIPostController.class)
class APIPostControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper mapper;

    @MockBean private PostService postService;

    public APIPostControllerTest(
            @Autowired MockMvc mvc,
            @Autowired ObjectMapper mapper
    ) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    @DisplayName("[API][POST] 포스트 생성")
    @Test
    void givenPost_whenCreatingPost_thenReturnsSuccessfulResponse() throws Exception {
        // Given
        PostRequest postRequest = PostRequest.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();

        // When & Then
        mvc.perform(
                post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(postRequest))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("[API][GET] 단일 포스트 조회 - 장소 있는 경우")
    @Test
    void givenPostId_whenRequestingExistentPost_thenReturnsPost() throws Exception {
        // Given
        Long postId = 1L;

        // When & Then

    }

//    @Test
//    //@WithMockUser(roles = "USER")
//    public void save_posts() throws Exception {
//
//        // given
//        String title = "title";
//        String content = "content";
//        PostRequest requestDto = PostRequest.builder()
//                                                                .title(title)
//                                                                .content(content)
//                                                                .author("author")
//                                                                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts";
//
//        // when
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        List<Post> all = postRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//
//    @Test
//    //@WithMockUser(roles = "USER")
//    public void update_posts() throws Exception {
//
//        // given
//        Post savedPost = postRepository.save(Post.builder()
//                                                        .title("title")
//                                                        .content("content")
//                                                        .author("author")
//                                                        .build());
//
//        Long updateId = savedPost.getId();
//        String expectedTitle   = "title2";
//        String expectedContent = "content2";
//
//        PostUpdateRequest requestDto = PostUpdateRequest.builder()
//                                                                    .title(expectedTitle)
//                                                                    .content(expectedContent)
//                                                                    .build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
//
//        HttpEntity<PostUpdateRequest> requestEntity = new HttpEntity<>(requestDto);
//
//        // when
//        mvc.perform(put(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        // then
//        List<Post> all = postRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//    }
}
