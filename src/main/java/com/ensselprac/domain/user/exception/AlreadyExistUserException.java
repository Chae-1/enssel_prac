package com.ensselprac.domain.user.exception;

public class AlreadyExistUserException extends UserException {

    public AlreadyExistUserException() {
        this("이미 등록되어있는 아이디입니다.");
    }

    public AlreadyExistUserException(String message) {
        super(message);
    }

    public AlreadyExistUserException(UserException exception) {
        super(exception);
    }
}
