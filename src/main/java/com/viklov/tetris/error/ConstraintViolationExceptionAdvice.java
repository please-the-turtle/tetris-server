package com.viklov.tetris.error;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ConstraintViolationExceptionAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> exceptionConstraintViolationHandler(ConstraintViolationException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        String errorMessage = e.getConstraintViolations().stream()
                .map( cv -> cv == null ? "Constraint violations." : cv.getMessage() )
                .collect( Collectors.joining( " " ) );
        errorDetails.setMessage(errorMessage);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
