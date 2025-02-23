package com.mechat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.mechat.service.UserService;
import com.mechat.webSocket.AuthHandshakeInterceptor;
import com.mechat.webSocket.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private UserService userService;

    @Value("${websocket.path}")
    private String websocketPath;

    @Value("${websocket.allowed-origins}")
    private String allowedOrigins;

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(), websocketPath)
                .setAllowedOrigins(allowedOrigins)
                .addInterceptors(new AuthHandshakeInterceptor(userService));
    }
}
