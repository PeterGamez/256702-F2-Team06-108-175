package com.mechat.webSocket.events;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Chat;
import com.mechat.service.ChatService;
import com.mechat.webSocket.RequestMessage;
import com.mechat.webSocket.ResponseMessage;
import com.mechat.webSocket.interfaces.EventInterface;

@Component
public class MessageEvent implements EventInterface {

    @Autowired
    private ChatService chatService;

    private static int op = 11;
    private static int responseOp = 12;

    public int getOp() {
        return op;
    }

    public int getResponseOp() {
        return responseOp;
    }

    private Set<WebSocketSession> sessions;
    private WebSocketSession session;
    private RequestMessage request;
    private UserDTO user;

    public void handle(Set<WebSocketSession> sessions, WebSocketSession session, RequestMessage request, UserDTO user) {
        this.sessions = sessions;
        this.session = session;
        this.request = request;
        this.user = user;

        if (request.getT() == 1) {
            sendToPrivateChat();
        } else if (request.getT() == 2) {
            sendToGroupChat();
        }
    }

    private void sendToPrivateChat() {
        String chatStr = request.getD().get("chat").toString();
        String message = request.getD().get("message").toString();

        Long chat_id = null;

        try {
            chat_id = Long.parseLong(chatStr);
        } catch (NumberFormatException e) {
            ResponseMessage response = new ResponseMessage(session, responseOp, 1);

            response.put("status", "error");
            response.put("message", "chat id is not valid");

            response.send();
            return;
        }

        Chat chat = chatService.getChatById(chat_id);
        if (chat == null) {
            ResponseMessage response = new ResponseMessage(session, responseOp, 1);

            response.put("status", "error");
            response.put("message", "chat not found");

            response.send();
            return;
        }

        chatService.saveChatHistory(chat, user, message);

        UserDTO chatUser = chatService.getChatUser(chat, user);

        Optional<WebSocketSession> sessionUser = sessions.stream()
                .filter(s -> s.isOpen() && s.getAttributes().get("user") != null)
                .filter(s -> {
                    UserDTO user = (UserDTO) s.getAttributes().get("user");
                    return user.getId() == chatUser.getId();
                })
                .findFirst();

        if (sessionUser.isPresent()) {
            ResponseMessage response = new ResponseMessage(sessionUser.get(), responseOp, 1);

            response.put("chat", chat.getId());
            response.put("message", message);

            response.send();
        }

        ResponseMessage response = new ResponseMessage(session, responseOp, 1);

        response.put("status", "success");

        response.send();
    }

    private void sendToGroupChat() {
        String chatStr = request.getD().get("chat").toString();
        String message = request.getD().get("message").toString();

        Long chat_id = null;

        try {
            chat_id = Long.parseLong(chatStr);
        } catch (NumberFormatException e) {
            ResponseMessage response = new ResponseMessage(session, responseOp, 1);

            response.put("status", "error");
            response.put("message", "chat id is not valid");

            response.send();
            return;
        }

        Chat chat = chatService.getChatById(chat_id);
        if (chat == null) {
            ResponseMessage response = new ResponseMessage(session, responseOp, 1);

            response.put("status", "error");
            response.put("message", "chat not found");

            response.send();
            return;
        }

        chatService.saveChatHistory(chat, user, message);

        List<UserDTO> chatUsers = chatService.getChatUsers(chat, user);

        sessions.stream()
                .filter(s -> s.isOpen() && s.getAttributes().get("user") != null)
                .filter(s -> {
                    UserDTO user = (UserDTO) s.getAttributes().get("user");
                    return chatUsers.stream().anyMatch(u -> u.getId() == user.getId());
                })
                .forEach(s -> {
                    ResponseMessage response = new ResponseMessage(s, responseOp, 1);

                    response.put("chat", chat.getId());
                    response.put("sender", user.getId());
                    response.put("message", message);

                    response.send();
                });

        ResponseMessage response = new ResponseMessage(session, responseOp, 1);

        response.put("status", "success");

        response.send();
    }
}
