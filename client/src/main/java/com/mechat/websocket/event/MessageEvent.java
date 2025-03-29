package com.mechat.websocket.event;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.controller.chat.ChatController;
import com.mechat.service.RequestMessage;

import javafx.application.Platform;

public class MessageEvent {

    private static final Logger log = LoggerFactory.getLogger(MessageEvent.class);

    private static RequestMessage request;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void handle(RequestMessage request) {
        MessageEvent.request = request;

        if (request.getT() == 1) {
            log.info("Send message to private chat: " + request.getD().toString());
            sendToPrivateChat(1);
        } else if (request.getT() == 2) {
            log.info("Send message to group chat: " + request.getD().toString());
            sendToGroupChat(2);
        } else if (request.getT() == 6) {
            log.info("Update message: " + request.getD().toString());
            updateMessage(6);
        } else if (request.getT() == 7) {
            log.info("Delete message: " + request.getD().toString());
            deleteMessage(7);
        }
    }

    private static void sendToPrivateChat(int responseType) {
        String chatId = Objects.toString(request.getD().get("chat_id"));
        String message = Objects.toString(request.getD().get("message"));

        String currentChatId = MakeCache.getChatId();
        if (chatId.equals(currentChatId)) {
            Platform.runLater(() -> {
                MakeCache.getController(ChatController.class).reciveMessage(message);
            });
        }
    }

    private static void sendToGroupChat(int responseType) {
        // Object chatId = request.getD().get("chat_id");
        // Object senderId = request.getD().get("sender_id");
        // Object message = request.getD().get("message");
    }

    private static void updateMessage(int responseType) {
        // Object chatId = request.getD().get("chat_id");
        // Object messageId = request.getD().get("message_id");
        // Object message = request.getD().get("message");
    }

    private static void deleteMessage(int responseType) {
        // Object chatId = request.getD().get("chat_id");
        // Object messageId = request.getD().get("message_id");
    }
}
