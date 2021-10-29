package com.pipiolo.springboot.exception;

import lombok.Getter;

import static com.pipiolo.springboot.exception.ErrorCode.INTERNAL_ERROR;

@Getter
public class APIException extends RuntimeException {

    private final ErrorCode errorCode;

    public APIException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public APIException(String message) {
        super(message);
        this.errorCode = INTERNAL_ERROR;
    }
}
