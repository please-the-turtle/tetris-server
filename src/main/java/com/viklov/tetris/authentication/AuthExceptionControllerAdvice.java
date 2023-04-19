package com.viklov.tetris.authentication;

import com.viklov.tetris.error.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionControllerAdvice {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetails> exceptionAuthHandler(AuthException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }
}
