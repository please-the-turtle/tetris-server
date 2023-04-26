package com.viklov.tetris.authentication.jwt;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException() {
        super("Invalid authorization token.");
    }
}
