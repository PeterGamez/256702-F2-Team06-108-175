package com.mechat.websocket;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;

import jakarta.websocket.CloseReason;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

public class WebSocketEndpoint extends Endpoint {

    private static final Logger log = LoggerFactory.getLogger(WebSocketEndpoint.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        MakeCache.setSession(session);

        log.info("WebSocket connection opened: " + session.getId());

        addMessageHandler(session);

        // get token payload
        String token = MakeCache.getAuthToken();
        String[] tokenParts = token.split("\\.");

        String payload = new String(java.util.Base64.getDecoder().decode(tokenParts[1]));

        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });
            MakeCache.setUser(respond);
        } catch (Exception e) {
        }
    }

    private void addMessageHandler(Session session) {
        session.addMessageHandler(new MessageHandler.Whole<String>() {

            @Override
            public void onMessage(String message) {
                WebSocketMessage.handle(message);
            }
        });
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        MakeCache.setSession(null);

        WebSocketClient.reconnect();

        log.info("Session closed: " + closeReason.getCloseCode() + " - " + closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("Error occurred: " + throwable.getMessage());
    }
}
