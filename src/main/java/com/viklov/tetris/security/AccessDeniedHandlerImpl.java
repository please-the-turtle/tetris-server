package com.viklov.tetris.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viklov.tetris.error.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("Content-Type", "application/json");
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Access denied.");
        response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
    }
}
