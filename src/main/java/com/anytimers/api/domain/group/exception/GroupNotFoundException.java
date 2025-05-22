package com.anytimers.api.domain.group.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super();
    }

    public GroupNotFoundException(String message) {
        super(message);
    }
}
