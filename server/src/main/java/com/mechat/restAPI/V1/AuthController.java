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
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> payload) {
        if (!payload.containsKey("username") || !payload.containsKey("password")) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid request");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userService.login(payload.get("username").toString(), payload.get("password").toString());
        if (user == null) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid username or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());

        String token = JWT.generateToken(claims);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        String hash = Crypt.encrypt("1");
        
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("hash", hash);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
