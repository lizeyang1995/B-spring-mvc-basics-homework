package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private String errorMessage;

    public UserAlreadyExistsException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
