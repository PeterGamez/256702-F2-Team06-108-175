package com.mechat.webSocket;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.mechat.dto.UserDTO;
import com.mechat.service.UserService;
import com.mechat.utils.JWT;

import java.util.Map;

@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private UserService userService;

    public AuthHandshakeInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
        String authToken = request.getHeaders().getFirst("Authorization");

        if (authToken != null && validateToken(authToken)) {
            Map<String, Object> payload = JWT.getPayload(authToken.split(" ")[1]);

            UserDTO user = userService.getUserById(Long.parseLong(payload.get("id").toString()));
            if (user == null) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }

            attributes.put("user", user);

            return true;
        } else {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @Nullable Exception exception) {
    }

    private boolean validateToken(String authToken) {
        String authType = authToken.split(" ")[0];
        String token = authToken.split(" ")[1];

        if (!authType.equals("Bearer")) {
            return false;
        }

        if (JWT.validateToken(token) == false) {
            return false;
        }

        return true;
    }
}
