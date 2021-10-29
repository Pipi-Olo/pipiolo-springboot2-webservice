package com.pipiolo.springboot.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipiolo.springboot.dto.PostRequest;
import com.pipiolo.springboot.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.pipiolo.springboot.exception.ErrorCode.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(postRequest))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(OK.getCode()))
                .andExpect(jsonPath("$.message").value(OK.getMessage()));
    }

    @DisplayName("[API][GET] 단일 포스트 조회 - 장소 있는 경우")
    @Test
    void givenPostId_whenRequestingExistentPost_thenReturnsPost() throws Exception {
        // Given
        Long postId = 1L;

        // When & Then
        mvc.perform(
                get("/api/v1/posts/" + postId)
        )
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(OK.getCode()))
                .andExpect(jsonPath("$.message").value(OK.getMessage()));

    }

    @DisplayName("[API][GET] 단일 포스트 조회 - 잘못된 파라미터")
    @Test
    void givenPostId_whenRequestingNonExistentPost_thenReturnsFailed() throws Exception {
        // Given
        Long postId = -1L;

        // When & Then
        mvc.perform(
                get("/api/v1/posts/" + postId)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(VALIDATION_ERROR.getCode()))
                .andExpect(jsonPath("$.message").value(containsString(VALIDATION_ERROR.getMessage())));
    }

    @DisplayName("[API][GET] 리스트 포스트 조회")
    @Test
    void givenNothing_whenRequestPosts_thenReturnsListOfPost() throws Exception {
        // Given

        // When & Then
        mvc.perform(
                        get("/api/v1/posts")
                                .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(OK.getCode()))
                .andExpect(jsonPath("$.message").value(OK.getMessage()));

    }

    @DisplayName("[API][PUT] 포스트 변경")
    @Test
    void givenPostIdAndInfo_whenUpdatePost_thenReturnsSuccessful() throws Exception{
        // Given
        Long postId = 1L;
        PostRequest request = PostRequest.builder()
                .title("title2")
                .content("content2")
                .author("author2")
                .build();

        // When & Then
        mvc.perform(
                put("/api/v1/posts/" + postId)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(OK.getCode()))
                .andExpect(jsonPath("$.message").value(OK.getMessage()));
    }

    @DisplayName("[API][DELETE] 포스트 삭제")
    @Test
    void givenPostId_whenDeletePost_thenReturnsSuccessful() throws Exception {
        // Given
        Long postId = 1L;

        // When & Then
        mvc.perform(
                delete("/api/v1/posts/" + postId)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(OK.getCode()))
                .andExpect(jsonPath("$.message").value(OK.getMessage()));
    }

    @DisplayName("[API][DELETE] 포스트 삭제 - 잘못된 파라미터")
    @Test
    void givenWrongPostId_whenDeletePost_thenReturnsFailed() throws Exception {
        // Given
        Long postId = -1L;

        // When & Then
        mvc.perform(delete("/api/v1/posts/" + postId))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(VALIDATION_ERROR.getCode()))
                .andExpect(jsonPath("$.message").value(containsString(VALIDATION_ERROR.getMessage())));
    }
}
