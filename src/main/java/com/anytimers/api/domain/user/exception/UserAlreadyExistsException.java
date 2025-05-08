package com.anytimers.api.domain.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to create a user with a unique field (such as email) that already exists.
 * <p>
 * The default constructor references the email field.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("A user with this email already exists.");
    }
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
    public UserAlreadyExistsException(String field, String value) {
        super("A user with this " + field + " (" + value + ") already exists" );
    }
}
