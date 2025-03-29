package com.mechat.webSocket.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.dto.UserDTO;
import com.mechat.entity.Chat;
import com.mechat.entity.ChatMember;
import com.mechat.service.ChatService;
import com.mechat.service.UserService;
import com.mechat.webSocket.RequestMessage;
import com.mechat.webSocket.ResponseMessage;
import com.mechat.webSocket.interfaces.EventInterface;

@Component
public class ChatEvent implements EventInterface {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    private static int op = 13;
    private static int responseOp = 14;

    private Set<WebSocketSession> sessions;
    private WebSocketSession session;
    private RequestMessage request;
    private UserDTO user;

    private ObjectMapper mapper = new ObjectMapper();

    public int getOp() {
        return op;
    }

    public int getResponseOp() {
        return responseOp;
    }

    public void handle(Set<WebSocketSession> sessions, WebSocketSession session, RequestMessage request, UserDTO user) {
        this.sessions = sessions;
        this.session = session;
        this.request = request;
        this.user = user;

        if (request.getT() == 1) {
            createChat(1);
        } else if (request.getT() == 2) {
            updateChat(2);
        }
    }

    private void createChat(int responseType) {
        String userStr = request.getD().get("user_ids").toString();
        String typeStr = request.getD().get("type").toString();

        List<Long> userIds;
        try {
            userIds = mapper.readValue(userStr, new TypeReference<List<Long>>() {
            });
        } catch (Exception e) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);
            response.put("status", "error");
            response.put("message", "Invalid user IDs format");
            response.send();
            return;
        }

        Chat.Type chatType = Chat.Type.fromValue(typeStr);

        List<String> chatUserList = new ArrayList<>();

        Chat chat = new Chat();
        if (chatType == Chat.Type.PRIVATE) {
            if (userIds.size() != 2) {
                ResponseMessage response = new ResponseMessage(session, responseOp, responseType);
                response.put("status", "error");
                response.put("message", "PRIVATE chat must have exactly 2 users");
                response.send();
                return;
            }
        } else if (chatType == Chat.Type.GROUP) {
            userIds.forEach(u -> {
                UserDTO user = userService.getUserById(u);
                chatUserList.add(user.getDisplayName() != null ? user.getDisplayName() : user.getUsername());
            });

            String chatName = chatUserList.size() > 0 ? String.join(", ", chatUserList) : null;
            chat.setName(chatName);
        }
        chat.setType(chatType);

        chat = chatService.addChat(chat, userIds);
        Chat chatA = chat;

        sessions.stream()
                .filter(s -> s.isOpen() && s.getAttributes().get("user") != null)
                .filter(s -> {
                    UserDTO user = (UserDTO) s.getAttributes().get("user");
                    return !user.getId().equals(this.user.getId()) && userIds.contains(user.getId());
                })
                .forEach(s -> {
                    ResponseMessage response = new ResponseMessage(s, responseOp, responseType);

                    response.put("chat_id", chatA.getId());

                    response.send();
                });

        ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

        response.put("status", "success");
        response.put("chat_id", chat.getId());

        response.send();
    }

    private void updateChat(int responseType) {
        String chatStr = request.getD().get("chat_id").toString();
        String chat = request.getD().get("chat").toString();
        String addUserStr = request.getD().get("add_users").toString();
        String removeUserStr = request.getD().get("remove_users").toString();

        Long chatId;

        try {
            chatId = Long.parseLong(chatStr);
        } catch (Exception e) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);
            response.put("status", "error");
            response.put("message", "Invalid chat ID format");
            response.send();
            return;
        }

        Chat chatObj = chatService.getChatById(chatId);

        if (chatObj == null) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);
            response.put("status", "error");
            response.put("message", "Chat not found");
            response.send();
            return;
        }

        List<Long> addUsers;
        List<Long> removeUsers;

        try {
            addUsers = mapper.readValue(addUserStr, new TypeReference<List<Long>>() {
            });
            removeUsers = mapper.readValue(removeUserStr, new TypeReference<List<Long>>() {
            });
        } catch (Exception e) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);
            response.put("status", "error");
            response.put("message", "Invalid user IDs format");
            response.send();
            return;
        }

        chatService.updateChat(chatObj, addUsers, removeUsers);

        List<ChatMember> chatMembers = chatService.getChatUsers(chatObj);
        List<String> users = new ArrayList<>();
        chatMembers.forEach(cm -> {
            UserDTO user = userService.convertToDTO(cm.getUser());
            users.add(user.getDisplayName() != null ? user.getDisplayName() : user.getUsername());
        });
        String usersJson = "[]";
        try {
            usersJson = mapper.writeValueAsString(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String usersData = usersJson;

        sessions.stream()
                .filter(s -> s.isOpen() && s.getAttributes().get("user") != null)
                .forEach(s -> {
                    ResponseMessage response = new ResponseMessage(s, responseOp, responseType);

                    response.put("chat_id", chatId);
                    response.put("chat", chat);
                    response.put("users", usersData);

                    response.send();
                });
    }
}
