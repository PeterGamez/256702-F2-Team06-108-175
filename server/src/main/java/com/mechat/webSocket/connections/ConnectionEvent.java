package com.mechat.webSocket.connections;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Chat;
import com.mechat.entity.Friend;
import com.mechat.service.ChatService;
import com.mechat.service.FriendService;
import com.mechat.webSocket.ResponseMessage;
import com.mechat.webSocket.interfaces.ConnectionInterface;

public class ConnectionEvent implements ConnectionInterface {

    @Autowired
    ChatService chatService;
    FriendService friendService;

    private static int responseOp = 1;

    public int getResponseOp() {
        return responseOp;
    }

    public void handle(Set<WebSocketSession> sessions, WebSocketSession session, UserDTO user) {
        ResponseMessage response = new ResponseMessage(responseOp, 1);

        List<Chat> chats = chatService.getChats(user.getId());
        List<Friend> friends = friendService.getFriends(user.getId());

        response.put("chats", chats);
        response.put("friends", friends);

        try {
            session.sendMessage(response.send());
        } catch (Exception e) {
        }
    }
}
