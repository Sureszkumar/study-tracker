package com.sample.service.exception;

public class UserServiceException extends RuntimeException {

    public UserServiceException(final String message) {
        super(message);
    }
}
