package com.pipiolo.springboot.exception;

import com.pipiolo.springboot.dto.APIErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.pipiolo.springboot.exception.ErrorCode.BAD_REQUEST;
import static com.pipiolo.springboot.exception.ErrorCode.INTERNAL_ERROR;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(APIException.class)
    public APIErrorResponse handleException(
            APIException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getErrorCode(), request.getRequestURI(), e.getMessage());

        return APIErrorResponse.of(e);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public APIErrorResponse handleBadRequest(
            APIException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getErrorCode(), request.getRequestURI(), e.getMessage());

        return APIErrorResponse.of(BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public APIErrorResponse handleGeneralException(
            APIException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getErrorCode(), request.getRequestURI(), e.getMessage());

        return APIErrorResponse.of(INTERNAL_ERROR);
    }

}
