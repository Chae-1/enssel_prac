package com.ensselprac.domain.user.exception;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public UserException(UserException cause) {
        super(cause);
    }

    public UserException(String message, UserException cause) {
        super(message, cause);
    }
}
