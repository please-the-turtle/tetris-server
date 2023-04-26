package com.viklov.tetris.authentication;

import com.viklov.tetris.authentication.jwt.InvalidTokenException;
import com.viklov.tetris.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionControllerAdvice {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDetails> exceptionAuthHandler(InvalidTokenException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorDetails);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDetails> exceptionInvalidPasswordHandler(InvalidPasswordException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
}
