package com.viklov.tetris.controller;

import com.viklov.tetris.exception.AuthException;
import com.viklov.tetris.exception.UserAlreadyExistsException;
import com.viklov.tetris.exception.UserNotFoundException;
import com.viklov.tetris.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionUserNotFoundHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("User not found.");

        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> exceptionUserAlreadyExistsHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("User with this username is already registered.");

        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetails> exceptionAuthHandler(AuthException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }
}
