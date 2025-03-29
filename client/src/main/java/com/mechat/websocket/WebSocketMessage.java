package com.mechat.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mechat.MakeCache;
import com.mechat.controller.navbar.HomeController;
import com.mechat.service.RequestMessage;
import com.mechat.websocket.event.ChatEvent;
import com.mechat.websocket.event.FriendEvent;
import com.mechat.websocket.event.MessageEvent;

import javafx.application.Platform;

public class WebSocketMessage {

    private static final Logger log = LoggerFactory.getLogger(WebSocketMessage.class);

    public static void handle(String payload) {
        log.info("WebSocketMessage: " + payload);

        RequestMessage request = new RequestMessage(payload);

        if (request.getOp() == 1) {
            Object chats = request.getD().get("chats");
            Object friends = request.getD().get("friends");

            MakeCache.setData("chats", chats);
            MakeCache.setData("friends", friends);

            Platform.runLater(() -> {
                MakeCache.getController(HomeController.class).loadChats();
            });
        } else if (request.getOp() == 2) {
            Object users = request.getD().get("users");

            MakeCache.setData("userOnline", users);
        } else if (request.getOp() == 12) {
            MessageEvent.handle(request);
        } else if (request.getOp() == 14) {
            ChatEvent.handle(request);
        } else if (request.getOp() == 17) {
            FriendEvent.handle(request);
        }
    }
}
