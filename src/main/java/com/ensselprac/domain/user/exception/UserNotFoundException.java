package com.ensselprac.domain.user.exception;

public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        this("회원 정보를 조회할 수 없습니다.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
