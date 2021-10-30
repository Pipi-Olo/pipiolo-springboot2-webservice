package com.pipiolo.springboot.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Deprecated
@Disabled("뷰 컨트롤러 변경 예정")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void road_mainPage() {

        // when
        String body = this.restTemplate.getForObject("/", String.class);

        // then
        assertThat(body).contains("SpringBoot Webservice");
    }
}
