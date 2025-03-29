package com.mechat.webSocket.connections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Chat;
import com.mechat.entity.Friend;
import com.mechat.service.ChatService;
import com.mechat.service.FriendService;
import com.mechat.webSocket.ResponseMessage;
import com.mechat.webSocket.interfaces.ConnectionInterface;

@Component
public class ConnectionEvent implements ConnectionInterface {

    @Autowired
    ChatService chatService;

    @Autowired
    FriendService friendService;

    private static int responseOp = 1;

    public int getResponseOp() {
        return responseOp;
    }

    public void handle(Set<WebSocketSession> sessions, WebSocketSession session, UserDTO user) {
        ResponseMessage response = new ResponseMessage(session, responseOp, 1);

        List<Chat> chats = chatService.getChatsByUserId(user.getId());
        List<Friend> friends = friendService.getFriends(user.getId());

        List<UserDTO> userDTOs = new ArrayList<>();
        friends.forEach(d-> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(d.getFriend().getId());
            userDTO.setUsername(d.getFriend().getUsername());
            userDTO.setDisplayName(d.getFriend().getDisplayName());
            userDTO.setAvatar(d.getFriend().getAvatar());
            userDTOs.add(userDTO);
        });

        response.put("chats", chats);
        response.put("friends", userDTOs);

        response.send();
    }
}
