package com.mechat.service;

import java.time.Duration;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.mechat.MakeCache;

import reactor.core.publisher.Mono;

public class RestApiService {

    private static final Logger log = LoggerFactory.getLogger(RestApiService.class);

    private static WebClient webClient;

    public static void connect() {
        log.info("Connecting to server...");

        String serverIp = Objects.toString(MakeCache.getServer().get("serverIp"));
        String serverPort = Objects.toString(MakeCache.getServer().get("serverPort"));
        RestApiService.webClient = WebClient.builder().baseUrl("http://" + serverIp + ":" + serverPort).build();

        log.info("Connected to server: " + serverIp + ":" + serverPort);
    }

    public static void disconnect() {
        log.info("Disconnecting from server...");
        RestApiService.webClient = null;
    }

    public static Mono<String> getConnection(String serverIp, String serverPort) throws Exception {
        log.info("Check Connecting to server: " + serverIp + ":" + serverPort);
        WebClient webClient = WebClient.builder().baseUrl("http://" + serverIp + ":" + serverPort).build();

        return webClient.get()
                .uri("/v1/connection")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .onErrorReturn("{\"message\": \"Offline\"}");
    }

    public static Mono<String> login(String username, String password) throws Exception {
        log.info("Login: " + username + " " + password);
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
        log.info("Register: " + username + " " + password);
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
        log.info("Get user: " + userId);
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
        log.info("Get user by username: " + username);
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
        log.info("Get chat: " + chatId);
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

    public static Mono<String> getChatHistory(String chatId) throws Exception {
        log.info("Get chat history: " + chatId);
        return webClient.get()
                .uri("/v1/chat/" + chatId + "/history")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> {
                    System.err.println(chatId);
                    System.err.println(error.getMessage());
                });
    }
}
