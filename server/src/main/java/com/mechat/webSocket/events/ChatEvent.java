package com.mechat.webSocket.events;

import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.webSocket.RequestMessage;
import com.mechat.webSocket.interfaces.EventInterface;

public class ChatEvent implements EventInterface {

    private static int op = 13;
    private static int responseOp = 14;

    public int getOp() {
        return op;
    }

    public int getResponseOp() {
        return responseOp;
    }

    public void handle(WebSocketSession session, RequestMessage request, UserDTO user) {
    }
}
