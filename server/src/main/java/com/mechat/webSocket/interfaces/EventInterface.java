package com.mechat.webSocket.interfaces;

import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.webSocket.RequestMessage;

public interface EventInterface {

    public int getOp();

    public int getResponseOp();

    public void handle(WebSocketSession session, RequestMessage request, UserDTO user);
}
