package com.mechat.restAPI.V1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechat.entity.User;
import com.mechat.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getUsers() { 
        return userService.getAllUsers();
    }
}
