package com.anytimers.api.domain.auth.exception;

/**
 * Custom exception for when wrong credentials are provided
 */
public class InvalidCredentialsException extends RuntimeException {
    
    public InvalidCredentialsException() {
        super();
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
