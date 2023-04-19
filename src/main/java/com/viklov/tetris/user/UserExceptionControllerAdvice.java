package com.viklov.tetris.user;

import com.viklov.tetris.error.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionControllerAdvice {

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
}
