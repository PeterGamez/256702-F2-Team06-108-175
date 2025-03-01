package com.mechat.webSocket.events;

import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.webSocket.RequestMessage;
import com.mechat.webSocket.ResponseMessage;
import com.mechat.webSocket.interfaces.EventInterface;

public class MessageEvent implements EventInterface {

    private static int op = 11;
    private static int responseOp = 12;

    public int getOp() {
        return op;
    }
    
    public int getResponseOp() {
        return responseOp;
    }

    public void handle(WebSocketSession session, RequestMessage request, UserDTO user) {
        if (request.getT() == 1) {
            String message = request.getD().get("message").toString();

            ResponseMessage response = new ResponseMessage(op, 2);

            response.put("status", "success");
            response.put("message", message);

            try {
                session.sendMessage(response.send());
            } catch (Exception e) {
            }
        }
    }
}
