package com.mechat.service;

import java.time.Duration;
import java.util.Objects;

import org.springframework.web.reactive.function.client.WebClient;

import com.mechat.MakeCache;

import reactor.core.publisher.Mono;

public class RestApiService {

    private static WebClient webClient;

    public static void connect() {
        String serverIp = Objects.toString(MakeCache.getServer().get("serverIp"));
        String serverPort = Objects.toString(MakeCache.getServer().get("serverPort"));
        RestApiService.webClient = WebClient.builder().baseUrl("http://" + serverIp + ":" + serverPort).build();
    }

    public static void disconnect() {
        RestApiService.webClient = null;
    }

    public static Mono<String> getConnection(String serverIp, String serverPort) throws Exception {
        WebClient webClient = WebClient.builder().baseUrl("http://" + serverIp + ":" + serverPort).build();

        return webClient.get()
                .uri("/v1/connection")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorReturn("{\"message\": \"Offline\"}");
    }

    public static Mono<String> login(String username, String password) throws Exception {
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

    public static Mono<String> register(String username, String password) throws Exception {
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

    public static Mono<String> getUser(String userId) throws Exception {
        return webClient.get()
                .uri("/v1/user/" + userId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> {
                    System.err.println(userId);
                    System.err.println(error.getMessage());
                });
    }

    public static Mono<String> getUserByUsername(String username) throws Exception {
        return webClient.get()
                .uri("/v1/user/search/" + username)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> {
                    System.err.println(username);
                    System.err.println(error.getMessage());
                });
    }

    public static Mono<String> getChat(String chatId) throws Exception {
        return webClient.get()
                .uri("/v1/chat/" + chatId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> {
                    System.err.println(chatId);
                    System.err.println(error.getMessage());
                });
    }
}
