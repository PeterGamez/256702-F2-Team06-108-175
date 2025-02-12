package com.mechat.webSocket;

import org.springframework.lang.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebSocketHandler extends TextWebSocketHandler {

    private static Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessions.add(session);

        ResponseMessage response = new ResponseMessage(1, 1);

        response.put("status", "success");

        try {
            session.sendMessage(response.send());
        } catch (IOException e) {
        }
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage data) {
        RequestMessage request = new RequestMessage(data.getPayload());

        if (request.getT() == 2) {
            String message = request.getD().get("message").toString();

            ResponseMessage response = new ResponseMessage(1, 1);

            response.put("status", "success");
            response.put("message", message);

            try {
                session.sendMessage(response.send());
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session,
            @NonNull org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }
}
