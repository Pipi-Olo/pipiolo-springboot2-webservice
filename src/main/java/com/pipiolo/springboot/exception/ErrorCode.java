package com.pipiolo.springboot.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, HttpStatus.OK, "OK"),

    BAD_REQUEST(10000, HttpStatus.BAD_REQUEST, "Bad request"),

    INTERNAL_ERROR(20000, HttpStatus.INTERNAL_SERVER_ERROR, "Internal error")

    ;

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;


    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.getCode());
    }
}
