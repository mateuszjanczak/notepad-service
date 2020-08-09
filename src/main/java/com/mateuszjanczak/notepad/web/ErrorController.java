package com.mateuszjanczak.notepad.web;

import com.mateuszjanczak.notepad.exception.Error;
import com.mateuszjanczak.notepad.exception.ValidationError;
import com.mateuszjanczak.notepad.notes.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ErrorController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handleAnyException(NoteNotFoundException ex) {
        String errorMessage = ex.getLocalizedMessage() == null ? ex.getLocalizedMessage() : ex.toString();
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> fieldsErrors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        String errorMessage = "Object validation failed. Check fields errors.";
        return new ValidationError(HttpStatus.BAD_REQUEST, errorMessage, fieldsErrors);
    }

}