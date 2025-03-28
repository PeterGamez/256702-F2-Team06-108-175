package com.mechat.service;

import java.time.Duration;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RestApiService {

    private final WebClient webClient;

    public RestApiService(String serverIp, String serverPort) {
        this.webClient = WebClient.builder().baseUrl("http://" + serverIp + ":" + serverPort).build();
    }

    public Mono<String> getConnection() throws Exception {
        return webClient.get()
                .uri("/v1/connection")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorReturn("{\"message\": \"Offline\"}");
    }

    public Mono<String> login(String username, String password) throws Exception {
        String body = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        return webClient.post()
                .uri("/v1/auth/login")
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> {
                    System.err.println(username + " " + password);
                    System.err.println(error.getMessage());
                });
    }

    public Mono<String> register(String username, String password) throws Exception {
        String body = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        return webClient.post()
                .uri("/v1/auth/register")
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> {
                    System.err.println(username + " " + password);
                    System.err.println(error.getMessage());
                });
    }
}
