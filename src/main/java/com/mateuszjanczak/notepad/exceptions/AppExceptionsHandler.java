package com.mateuszjanczak.notepad.exceptions;

import com.mateuszjanczak.notepad.notes.exceptions.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleAnyException(NoteNotFoundException ex, WebRequest request) {
        String errorMessage = ex.getLocalizedMessage() == null ? ex.getLocalizedMessage() : ex.toString();
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }

    @ExceptionHandler(value = {NoteNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleNoteNotFoundException(NoteNotFoundException ex, WebRequest request) {
        return new ErrorMessage(HttpStatus.NOT_FOUND, "The note doesn't exist");
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> errorList.add(fieldError.getField() + " : " + fieldError.getDefaultMessage()));

        return new ValidationErrorMessage(HttpStatus.BAD_REQUEST, "Validation failed", errorList);
    }

}