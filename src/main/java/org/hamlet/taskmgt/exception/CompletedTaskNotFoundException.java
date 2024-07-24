package org.hamlet.taskmgt.exception;

public class CompletedTaskNotFoundException extends RuntimeException {
    public CompletedTaskNotFoundException(String message) {
        super(message);
    }
}
