package com.mateuszjanczak.notepad.notes.exceptions;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(String message) {
        super(message);
    }

}
