package com.mateuszjanczak.notepad.notes.web;

import com.mateuszjanczak.notepad.exception.Error;
import com.mateuszjanczak.notepad.notes.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
public class NoteErrorController {

    @ExceptionHandler(value = {NoteNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error handleNoteNotFoundException(NoteNotFoundException ex, WebRequest request) {
        return new Error(HttpStatus.NOT_FOUND, "The note doesn't exist");
    }

}