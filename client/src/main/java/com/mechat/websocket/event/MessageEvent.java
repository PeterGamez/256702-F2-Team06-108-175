package com.mechat.websocket.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.service.RequestMessage;

public class MessageEvent {

    private static RequestMessage request;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void handle(RequestMessage request) {
        MessageEvent.request = request;

        if (request.getT() == 1) {
            sendToPrivateChat(1);
        } else if (request.getT() == 2) {
            sendToGroupChat(2);
        } else if (request.getT() == 6) {
            updateMessage(6);
        } else if (request.getT() == 7) {
            deleteMessage(7);
        }
    }

    private static void sendToPrivateChat(int responseType) {
        Object chatId = request.getD().get("chat_id");
        Object message = request.getD().get("message");
    }

    private static void sendToGroupChat(int responseType) {
        Object chatId = request.getD().get("chat_id");
        Object senderId = request.getD().get("sender_id");
        Object message = request.getD().get("message");
    }

    private static void updateMessage(int responseType) {
        Object chatId = request.getD().get("chat_id");
        Object messageId = request.getD().get("message_id");
        Object message = request.getD().get("message");
    }

    private static void deleteMessage(int responseType) {
        Object chatId = request.getD().get("chat_id");
        Object messageId = request.getD().get("message_id");
    }
}
