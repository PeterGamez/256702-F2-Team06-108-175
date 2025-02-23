package com.mechat.restAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping()
    public String index() {
        return "Hello, Spring Boot API!";
    }

    @GetMapping("/v1")
    public String getV1() {
        return "This is API version 1";
    }
}
