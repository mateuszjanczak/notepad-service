package com.mateuszjanczak.notepad.users.service;

import com.mateuszjanczak.notepad.users.dao.UserDao;
import com.mateuszjanczak.notepad.users.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private UserDao userRepository;

    public UserService(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
