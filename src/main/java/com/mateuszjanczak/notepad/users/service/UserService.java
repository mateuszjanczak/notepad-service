package com.mateuszjanczak.notepad.users.service;

import com.mateuszjanczak.notepad.users.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
    User save(User user);
    User loadUserByUsername(String username);

}
