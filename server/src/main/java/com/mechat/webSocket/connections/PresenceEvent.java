package com.mechat.webSocket.connections;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.webSocket.ResponseMessage;
import com.mechat.webSocket.interfaces.ConnectionInterface;

@Component
public class PresenceEvent implements ConnectionInterface {

    private static int responseOp = 2;

    public int getResponseOp() {
        return responseOp;
    }

    public void handle(Set<WebSocketSession> sessions, WebSocketSession session, UserDTO user) {
        ResponseMessage response = new ResponseMessage(responseOp, 1);

        ArrayList<Long> users = new ArrayList<>();
        for (WebSocketSession s : sessions) {
            UserDTO u = (UserDTO) s.getAttributes().get("user");
            users.add(u.getId());
        }

        response.put("users", users);

        sessions.forEach(s -> {
            try {
                s.sendMessage(response.send());
            } catch (Exception e) {
            }
        });
    }
}
