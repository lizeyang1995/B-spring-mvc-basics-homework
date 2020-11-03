package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserNameOrPasswordWrong extends RuntimeException {
    private String errorMessage;

    public UserNameOrPasswordWrong(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
