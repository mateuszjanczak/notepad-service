package com.mateuszjanczak.notepad.notes.web;

import com.mateuszjanczak.notepad.dto.ErrorResponse;
import com.mateuszjanczak.notepad.notes.exception.NoteNotFoundException;
import com.mateuszjanczak.notepad.notes.web.rest.NoteController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes  = NoteController.class)
public class NoteErrorController {

    @ExceptionHandler(value = NoteNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNoteNotFoundException(NoteNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "The note doesn't exist: id = " + ex.getMessage());
    }

}