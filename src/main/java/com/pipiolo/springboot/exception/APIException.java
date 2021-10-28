package com.pipiolo.springboot.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public APIException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public APIException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
