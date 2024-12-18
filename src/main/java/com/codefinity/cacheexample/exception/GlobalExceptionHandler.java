package com.codefinity.cacheexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException ex) {

        return new ResponseEntity<>(Error
                .builder()
                .errorMessage(ex.getMessage())
                .build(),
                HttpStatus.NOT_FOUND
        );
    }
}
