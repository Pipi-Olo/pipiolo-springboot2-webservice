package com.pipiolo.springboot.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.pipiolo.springboot.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("API Exception")
class APIExceptionTest {

    @DisplayName("[Exception] 예외 생성 시, 알맞은 메세지 - 에러코드")
    @MethodSource
    @ParameterizedTest(name = "[{index}] message({2}) : \"{1}\"")
    void givenException_whenInstantiating_thenContainsExceptionInformation(
            Throwable input,
            String expectedMessage,
            ErrorCode expectedErrorCode
    ) {
        // Given

        // When & Then
        assertThat(input)
                .isInstanceOf(input.getClass())
                .hasMessage(expectedMessage)
                .hasFieldOrPropertyWithValue("errorCode", expectedErrorCode);
    }

    static Stream<Arguments> givenException_whenInstantiating_thenContainsExceptionInformation() {
        String msg = INTERNAL_ERROR.getMessage();
        Throwable t = new RuntimeException("inner message");
        ErrorCode errorCode = INTERNAL_ERROR;

        return Stream.of(
                arguments(new APIException(INTERNAL_ERROR), msg, INTERNAL_ERROR),
                arguments(new APIException(msg), msg, INTERNAL_ERROR)
        );
    }
}