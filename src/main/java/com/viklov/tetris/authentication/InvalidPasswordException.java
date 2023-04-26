package com.viklov.tetris.authentication;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() {
        super("Invalid password.");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
