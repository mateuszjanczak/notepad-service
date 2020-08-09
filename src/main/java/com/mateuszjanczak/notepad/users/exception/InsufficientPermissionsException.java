package com.mateuszjanczak.notepad.users.exception;

public class InsufficientPermissionsException extends RuntimeException {

    public InsufficientPermissionsException() {

    }

    public InsufficientPermissionsException(String message) {
        super(message);
    }

}
