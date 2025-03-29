package com.mechat.service;

import com.mechat.MakeCache;
import com.mechat.controller.navbar.HomeController;

import javafx.application.Platform;

public class WebSocketMessage {

    public static void handle(String payload) {
        try {
            System.out.println("WebSocketMessage: " + payload);

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
                Object users = request.getD().get("chat");

                MakeCache.setData("userOnline", users);
            } else if (request.getOp() == 12) {
                if (request.getT() == 1) {
                    Object chatId = request.getD().get("chat_id");
                    Object message = request.getD().get("message");
                } else if (request.getT() == 2) {
                    Object chatId = request.getD().get("chat_id");
                    Object senderId = request.getD().get("sender_id");
                    Object message = request.getD().get("message");
                } else if (request.getT() == 6) {
                    Object chatId = request.getD().get("chat_id");
                    Object messageId = request.getD().get("message_id");
                    Object message = request.getD().get("message");
                } else if (request.getT() == 7) {
                    Object chatId = request.getD().get("chat_id");
                    Object messageId = request.getD().get("message_id");
                }
            } else if (request.getOp() == 14) {
                if (request.getT() == 1) {
                    Object chatId = request.getD().get("chat_id");
                } else if (request.getT() == 2) {
                    Object chatId = request.getD().get("chat_id");
                    Object chat = request.getD().get("chat");
                    Object users = request.getD().get("users");
                }
            } else if (request.getOp() == 17) {
                if (request.getT() == 1) {
                    Object userId = request.getD().get("user_id");
                } else if (request.getT() == 2) {
                    Object userId = request.getD().get("user_id");
                    Object type = request.getD().get("type");
                }
            }
        } catch (Exception e) {
            System.err.println("WebSocketError: " + e.getMessage());
        }
    }
}
