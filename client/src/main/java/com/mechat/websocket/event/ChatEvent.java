package com.mechat.websocket.event;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.service.RequestMessage;
import com.mechat.service.RestApiService;
import com.mechat.utils.MakeCache;


public class ChatEvent {

    private static final Logger log = LoggerFactory.getLogger(ChatEvent.class);

    private static RequestMessage request;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void handle(RequestMessage request) {
        ChatEvent.request = request;

        if (request.getT() == 1) {
            log.info("Create chat: " + request.getD().toString());
            createChat(1);
        } else if (request.getT() == 2) {
            log.info("Update chat: " + request.getD().toString());
            updateChat(2);
        }
    }

    private static void createChat(int responseType) {
        String chatId = Objects.toString(request.getD().get("chat_id"));
        Map<String, Object> chat = null;
        try {
            String payload = RestApiService.getChat(chatId).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            if (respond.get("status").toString().equals("error")) {
                return;
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> chatObj = (Map<String, Object>) respond.get("chat");

            chat = chatObj;
        } catch (Exception e) {
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> chats = (List<Map<String, Object>>) MakeCache.getData("chats");

        chats.add(chat);

        MakeCache.setData("chats", chats);
    }

    private static void updateChat(int responseType) {
        // Object chatId = request.getD().get("chat_id");
        // Object chat = request.getD().get("chat");
        // Object users = request.getD().get("users");
    }
}
