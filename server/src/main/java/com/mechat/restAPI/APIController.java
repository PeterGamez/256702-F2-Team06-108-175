package com.mechat.restAPI;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping()
    public ResponseEntity<?> index() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Hello, Spring Boot API!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v1")
    public ResponseEntity<?> getV1() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "This is API version 1");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v1/connection")
    public ResponseEntity<?> getConnection() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Online");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
