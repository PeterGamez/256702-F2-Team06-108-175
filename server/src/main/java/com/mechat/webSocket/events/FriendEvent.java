package com.mechat.webSocket.events;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Friend;
import com.mechat.service.FriendService;
import com.mechat.service.UserService;
import com.mechat.webSocket.RequestMessage;
import com.mechat.webSocket.ResponseMessage;
import com.mechat.webSocket.interfaces.EventInterface;

@Component
public class FriendEvent implements EventInterface {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    private static int op = 16;
    private static int responseOp = 17;

    private Set<WebSocketSession> sessions;
    private WebSocketSession session;
    private RequestMessage request;
    private UserDTO user;

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
            addFriend(1);
        } else if (request.getT() == 2) {
            updateFriend(2);
        }
    }

    private void addFriend(int responseType) {
        String userStr = request.getD().get("user_id").toString();

        Long userId;

        try {
            userId = Long.parseLong(userStr);
        } catch (NumberFormatException e) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

            response.put("status", "error");
            response.put("message", "Invalid user id");

            response.send();
            return;
        }

        UserDTO friend = userService.getUserById(userId);
        if (friend == null) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

            response.put("status", "error");
            response.put("message", "User not found");

            response.send();
            return;
        }

        boolean isFriend = friendService.getFriends(user.getId(), friend.getId()) != null;
        if (isFriend) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

            response.put("status", "error");
            response.put("message", "Already friends");

            response.send();
            return;
        }

        friendService.addFriend(user, friend);

        sessions.stream()
                .filter(s -> s.isOpen() && s.getAttributes().get("user") != null)
                .filter(s -> {
                    UserDTO user = (UserDTO) s.getAttributes().get("user");
                    return user.getId().equals(friend.getId());
                })
                .forEach(s -> {
                    ResponseMessage response = new ResponseMessage(s, responseOp, responseType);

                    response.put("user_id", user.getId());

                    response.send();
                });

        ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

        response.put("status", "success");
        response.put("user_id", friend.getId());

        response.send();
    }

    private void updateFriend(int responseType) {
        String userStr = request.getD().get("user_id").toString();
        String typeStr = request.getD().get("type").toString();

        Long userId;

        try {
            userId = Long.parseLong(userStr);
        } catch (NumberFormatException e) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

            response.put("status", "error");
            response.put("message", "Invalid user id");

            response.send();
            return;
        }

        Friend.Status type = Friend.Status.fromValue(typeStr);

        UserDTO friend = userService.getUserById(userId);
        if (friend == null) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

            response.put("status", "error");
            response.put("message", "User not found");

            response.send();
            return;
        }

        boolean isFriend = friendService.getFriends(user.getId(), friend.getId()) != null;
        if (!isFriend) {
            ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

            response.put("status", "error");
            response.put("message", "Not friends");

            response.send();
            return;
        }

        friendService.updateFriend(user, friend, type);

        sessions.stream()
                .filter(s -> s.isOpen() && s.getAttributes().get("user") != null)
                .filter(s -> {
                    UserDTO user = (UserDTO) s.getAttributes().get("user");
                    return user.getId().equals(friend.getId());
                })
                .forEach(s -> {
                    ResponseMessage response = new ResponseMessage(s, responseOp, responseType);

                    response.put("user_id", user.getId());
                    response.put("type", typeStr);

                    response.send();
                });

        ResponseMessage response = new ResponseMessage(session, responseOp, responseType);

        response.put("status", "success");
        response.put("user_id", friend.getId());

        response.send();
    }
}
