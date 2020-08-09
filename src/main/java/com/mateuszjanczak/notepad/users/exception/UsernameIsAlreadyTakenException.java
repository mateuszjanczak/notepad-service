package com.mateuszjanczak.notepad.users.exception;

public class UsernameIsAlreadyTakenException extends RuntimeException {

    public UsernameIsAlreadyTakenException() {
    }

    public UsernameIsAlreadyTakenException(String message) {
        super(message);
    }

}
