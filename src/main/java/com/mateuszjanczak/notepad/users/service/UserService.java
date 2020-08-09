package com.mateuszjanczak.notepad.users.service;

import com.mateuszjanczak.notepad.users.entity.User;

public interface UserService {

    User findByUsername(String username);
    User save(User user);
    User loadUserByUsername(String username);

}
