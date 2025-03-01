package com.mechat.webSocket;

import org.springframework.lang.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.mechat.dto.UserDTO;
import com.mechat.webSocket.connections.ConnectionEvent;
import com.mechat.webSocket.connections.PresenceEvent;
import com.mechat.webSocket.events.ChatEvent;
import com.mechat.webSocket.events.FriendEvent;
import com.mechat.webSocket.events.MessageEvent;
import com.mechat.webSocket.interfaces.EventInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WebSocketHandler extends TextWebSocketHandler {

    private static Set<WebSocketSession> sessions = new HashSet<>();
    private static ArrayList<EventInterface> events = new ArrayList<>();

    public WebSocketHandler() {
        events.add(new ChatEvent());
        events.add(new FriendEvent());
        events.add(new MessageEvent());
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessions.add(session);
        UserDTO user = (UserDTO) session.getAttributes().get("user");

        ConnectionEvent connectionEvent = new ConnectionEvent();
        connectionEvent.handle(sessions, session, user);

        PresenceEvent presenceEvent = new PresenceEvent();
        presenceEvent.handle(sessions, session, user);
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage data) {
        RequestMessage request = new RequestMessage(data.getPayload());
        UserDTO user = (UserDTO) session.getAttributes().get("user");

        for (EventInterface event : events) {
            if (event.getOp() == request.getOp()) {
                event.handle(session, request, user);
            }
        }
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }
}
