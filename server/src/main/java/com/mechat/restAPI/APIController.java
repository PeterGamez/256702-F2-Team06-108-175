package com.mechat.restAPI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            Path imagePath = Paths.get(getClass().getResource("/images/" + imageName).toURI());
            String imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();

            Resource resource = new UrlResource(imagePath.toUri());

            if (!resource.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok()
                    .header("Content-Type", "image/" + imageExtension)
                    .body(resource);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
