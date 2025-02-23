package com.mechat.restAPI.V1;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechat.dto.UserDTO;
import com.mechat.entity.User;
import com.mechat.service.UserService;
import com.mechat.utils.Crypt;
import com.mechat.utils.JWT;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> payload) {
        if (!payload.containsKey("username") || !payload.containsKey("password")) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid request");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String username = payload.get("username").toString();
        String password = payload.get("password").toString();

        UserDTO user = userService.login(username, password);
        if (user == null) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid username or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("displayName", user.getDisplayName());
        claims.put("avatar", user.getAvatar());

        String token = JWT.generateToken(claims);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("accessToken", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> payload) {
        if (!payload.containsKey("username") || !payload.containsKey("password")) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid request");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String username = payload.get("username").toString();
        String password = payload.get("password").toString();

        UserDTO isDuplicate = userService.getUserByUsername(username);
        if (isDuplicate != null) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "error");
            response.put("message", "Username already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        String hash = Crypt.encrypt(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hash);

        userService.createUser(user);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
