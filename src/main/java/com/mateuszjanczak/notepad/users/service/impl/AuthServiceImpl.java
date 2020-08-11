package com.mateuszjanczak.notepad.users.service.impl;

import com.mateuszjanczak.notepad.security.JwtProvider;
import com.mateuszjanczak.notepad.security.JwtToken;
import com.mateuszjanczak.notepad.users.dto.LoginRequest;
import com.mateuszjanczak.notepad.users.dto.RegisterRequest;
import com.mateuszjanczak.notepad.users.entity.Role;
import com.mateuszjanczak.notepad.users.entity.User;
import com.mateuszjanczak.notepad.users.exception.UsernameIsAlreadyTakenException;
import com.mateuszjanczak.notepad.users.service.AuthService;
import com.mateuszjanczak.notepad.users.service.RoleService;
import com.mateuszjanczak.notepad.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, UserService userService, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.roleService = roleService;
        this.userService = userService;
    }

    public JwtToken login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtProvider.createToken(user.getUsername());
        return new JwtToken(token);
    }

    public User register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        if(userService.findByUsername(username).isPresent()) throw new UsernameIsAlreadyTakenException(username);
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        Role roleUser = roleService.getRoleUser();
        user.setRoles(Collections.singletonList(roleUser));
        return userService.save(user);
    }

    public User getLoggedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.loadUserByUsername(user.getUsername());
    }
}
