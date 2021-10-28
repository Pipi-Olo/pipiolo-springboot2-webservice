package com.pipiolo.springboot.dto;

import com.pipiolo.springboot.exception.ErrorCode;

public class APIDataResponse<T> extends APIErrorResponse {

    private final T data;

    private APIDataResponse(T data) {
        super(true, ErrorCode.OK, "OK");
        this.data = data;
    }

    public static <T> APIDataResponse<T> of(T data) {
        return new APIDataResponse<>(data);
    }

    public static <T> APIDataResponse<T> empty() {
        return new APIDataResponse<>(null);
    }
}
