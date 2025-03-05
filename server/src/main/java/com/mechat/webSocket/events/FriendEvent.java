package com.mechat.webSocket.events;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.webSocket.RequestMessage;
import com.mechat.webSocket.interfaces.EventInterface;

@Component
public class FriendEvent implements EventInterface {

    private static int op = 16;
    private static int responseOp = 17;

    public int getOp() {
        return op;
    }

    public int getResponseOp() {
        return responseOp;
    }

    public void handle(Set<WebSocketSession> sessions, WebSocketSession session, RequestMessage request, UserDTO user) {
    }
}
