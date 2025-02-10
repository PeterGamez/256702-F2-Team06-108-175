package com.mechat.restAPI.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechat.entity.User;
import com.mechat.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
