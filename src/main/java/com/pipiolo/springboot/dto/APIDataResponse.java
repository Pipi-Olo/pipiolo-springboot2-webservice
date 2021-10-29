package com.pipiolo.springboot.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.pipiolo.springboot.exception.ErrorCode.OK;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class APIDataResponse<T> extends APIErrorResponse {

    private final T data;

    private APIDataResponse(T data) {
        super(true, OK.getCode(), OK.getMessage());
        this.data = data;
    }

    public static <T> APIDataResponse<T> of(T data) {
        return new APIDataResponse<>(data);
    }

    public static <T> APIDataResponse<T> empty() {
        return new APIDataResponse<>(null);
    }
}
