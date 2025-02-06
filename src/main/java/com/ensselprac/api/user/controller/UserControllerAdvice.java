package com.ensselprac.api.user.controller;

import com.ensselprac.api.ApiResponse;
import com.ensselprac.domain.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.ensselprac.api.user.*")
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleUserNotFoundException(UserNotFoundException e) {
        return ApiResponse.from(null, HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
