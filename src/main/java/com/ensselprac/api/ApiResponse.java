package com.ensselprac.api;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private final T data;
    private final HttpStatus status;
    private final String message;

    private ApiResponse(T data, HttpStatus status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> ApiResponse<T> from(T data, HttpStatus status, String message) {
        return new ApiResponse<T>(data, status, status.getReasonPhrase());
    }

    public static <T> ApiResponse<T> ok(T data) {
        HttpStatus ok = HttpStatus.OK;
        return from(data, ok, ok.getReasonPhrase());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
