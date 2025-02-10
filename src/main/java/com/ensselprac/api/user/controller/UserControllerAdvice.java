package com.ensselprac.api.user.controller;

import com.ensselprac.api.ApiResponse;
import com.ensselprac.domain.user.exception.AlreadyExistUserException;
import com.ensselprac.domain.user.exception.UserException;
import com.ensselprac.domain.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ensselprac.api.user.controller")
public class UserControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(UserControllerAdvice.class);

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleUserException(UserException e) {
        log.error("UserException: {}",e.getMessage(), e);
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> handleException(Exception e) {
        log.error("Exception: {}",e.getMessage(), e);
        return buildResponse("오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ApiResponse<String> buildResponse(String message, HttpStatus status) {
        return ApiResponse.from(message, HttpStatus.BAD_REQUEST, HttpStatus.BAD_GATEWAY.getReasonPhrase());
    }
}
