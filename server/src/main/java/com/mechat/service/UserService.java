package com.mechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechat.entity.UserEntity;
import com.mechat.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Number id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
}