package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Set;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity userExceptionHandler(MethodArgumentNotValidException exception) {
        String errorMessage;
        errorMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity userExceptionHandler(ConstraintViolationException exception) {
        String errorMessage = null;
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            errorMessage = constraintViolation.getMessage();
            break;
        }
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
