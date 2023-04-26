package com.viklov.tetris.error;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @NonNull ResponseEntity<Object> createResponseEntity(
            Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails();

        if (body instanceof ProblemDetail detail) {
            errorDetails.setMessage(detail.getDetail());
        } else {
            errorDetails.setMessage(statusCode.toString());
        }

        return new ResponseEntity<>(errorDetails, statusCode);
    }
}
