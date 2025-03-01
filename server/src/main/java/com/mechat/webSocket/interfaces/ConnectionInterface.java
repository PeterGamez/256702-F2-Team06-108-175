package com.mechat.webSocket.interfaces;

import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;

public interface ConnectionInterface {

    public int getResponseOp();

    public void handle(Set<WebSocketSession> sessions, WebSocketSession session, UserDTO user);
}
