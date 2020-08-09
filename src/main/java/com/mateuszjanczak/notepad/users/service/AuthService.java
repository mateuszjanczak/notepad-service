package com.mateuszjanczak.notepad.users.service;

import com.mateuszjanczak.notepad.users.dto.LoginRequest;
import com.mateuszjanczak.notepad.users.dto.RegisterRequest;
import com.mateuszjanczak.notepad.users.entity.User;
import com.mateuszjanczak.notepad.security.JwtToken;

public interface AuthService {

    JwtToken login(LoginRequest loginRequest);
    User register(RegisterRequest registerRequest);
    User getLoggedUser();

}
