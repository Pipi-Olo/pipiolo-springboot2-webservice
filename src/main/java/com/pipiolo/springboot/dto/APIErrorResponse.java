package com.pipiolo.springboot.dto;

import com.pipiolo.springboot.exception.ErrorCode;
import com.pipiolo.springboot.exception.APIException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class APIErrorResponse {

    private final Boolean success;
    private final ErrorCode errorCode;
    private final String message;

    public static APIErrorResponse of(APIException e) {
        return new APIErrorResponse(
                false,
                e.getErrorCode(),
                e.getMessage()
        );
    }

    public static APIErrorResponse of(ErrorCode errorCode) {
        return new APIErrorResponse(
                false,
                errorCode,
                errorCode.getMessage()
        );
    }
}
