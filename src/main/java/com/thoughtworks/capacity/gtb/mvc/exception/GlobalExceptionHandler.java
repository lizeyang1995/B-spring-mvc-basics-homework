package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity userExceptionHandler(UserAlreadyExistsException exception) {
        String errorMessage;
        errorMessage = exception.getMessage();
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}