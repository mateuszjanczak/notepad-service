package com.mateuszjanczak.notepad.users.web;

import com.mateuszjanczak.notepad.dto.ErrorResponse;
import com.mateuszjanczak.notepad.users.exception.UsernameIsAlreadyTakenException;
import com.mateuszjanczak.notepad.users.web.rest.AuthController;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes  = AuthController.class)
public class AuthErrorController {

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleJwtException(JwtException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleAuthenticationException(AuthenticationException ex) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "User not found: username = " + ex.getMessage());
    }


    @ExceptionHandler(UsernameIsAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleUsernameNotFoundException(UsernameIsAlreadyTakenException ex) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "Username is already taken: username = " + ex.getMessage());
    }
}