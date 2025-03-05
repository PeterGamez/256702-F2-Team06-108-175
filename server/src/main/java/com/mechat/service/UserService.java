package com.mechat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechat.dto.UserDTO;
import com.mechat.entity.User;
import com.mechat.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setDisplayName(user.getDisplayName());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setDisplayName(userDTO.getDisplayName());
        user.setAvatar(userDTO.getAvatar());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        return user;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return convertToDTO(userRepository.findById(id).orElse(null));
    }

    public UserDTO getUserByUsername(String username) {
        return convertToDTO(userRepository.findByUsername(username).orElse(null));
    }

    public UserDTO login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null || !user.verifyPassword(password)) {
            return null;
        }
        return convertToDTO(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
