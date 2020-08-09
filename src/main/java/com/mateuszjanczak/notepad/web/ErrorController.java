package com.mateuszjanczak.notepad.web;

import com.mateuszjanczak.notepad.dto.ErrorResponse;
import com.mateuszjanczak.notepad.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleAnyException(Exception ex) {
        String errorMessage = ex.getLocalizedMessage() == null ? ex.getLocalizedMessage() : ex.toString();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> fieldsErrors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        String errorMessage = "Object validation failed. Check fields errors.";
        return new ValidationErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, fieldsErrors);
    }

}