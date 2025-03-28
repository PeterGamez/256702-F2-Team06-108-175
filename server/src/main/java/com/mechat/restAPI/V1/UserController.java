package com.mechat.restAPI.V1;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechat.dto.UserDTO;
import com.mechat.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "error");
            response.put("message", "User not found");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}
