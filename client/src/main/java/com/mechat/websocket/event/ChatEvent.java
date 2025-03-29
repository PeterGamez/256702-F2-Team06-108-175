package com.mechat.websocket.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.service.RequestMessage;

public class ChatEvent {

    private static RequestMessage request;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void handle(RequestMessage request) {
        ChatEvent.request = request;

        if (request.getT() == 1) {
            createChat(1);
        } else if (request.getT() == 2) {
            updateChat(2);
        }
    }

    private static void createChat(int responseType) {
        Object chatId = request.getD().get("chat_id");

        
    }

    private static void updateChat(int responseType) {
        Object chatId = request.getD().get("chat_id");
        Object chat = request.getD().get("chat");
        Object users = request.getD().get("users");
    }
}
