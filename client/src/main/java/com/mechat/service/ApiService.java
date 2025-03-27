package com.mechat.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ApiService {
    private final WebClient webClient;

    public ApiService(String serverIp, String serverPort) {
        this.webClient = WebClient.builder().baseUrl("http://" + serverIp + ":" + serverPort).build();
    }

    public Mono<String> getConnection() throws Exception {
        return webClient.get()
                .uri("/v1/connection")
                .retrieve()
                .bodyToMono(String.class);
    }
}
