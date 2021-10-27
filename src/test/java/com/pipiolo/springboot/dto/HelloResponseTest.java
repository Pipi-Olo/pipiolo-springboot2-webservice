package com.pipiolo.springboot.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseTest {

    @Test
    public void lombok_function_test() {

        // given
        String name = "test";
        int amount  = 1000;

        // when
        HelloResponse dto = new HelloResponse(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
