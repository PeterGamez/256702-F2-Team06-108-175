package com.mechat.websocket.event;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.service.RequestMessage;
import com.mechat.service.ResponseMessage;
import com.mechat.service.RestApiService;
import com.mechat.websocket.WebSocketClient;

public class FriendEvent {

    private static RequestMessage request;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void handle(RequestMessage request) {
        FriendEvent.request = request;

        if (request.getT() == 1) {
            addFriend(1);
        } else if (request.getT() == 2) {
            updateFriend(2);
        }
    }

    private static void addFriend(int responseType) {
        String userId = Objects.toString(request.getD().get("user_id"));

        Map<String, Object> friend = null;
        try {
            String payload = RestApiService.getUser(userId).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            if (respond.get("status").toString().equals("error")) {
                return;
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> user = (Map<String, Object>) respond.get("user");

            friend = user;
        } catch (Exception e) {
        }

        if (friend == null) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> friends = (List<Map<String, Object>>) MakeCache.getData("friends");

        friends.add(friend);

        MakeCache.setData("friends", friends);

        ResponseMessage respond = new ResponseMessage(13, 1);

        String myId = Objects.toString(MakeCache.getUser().get("id"));
        respond.put("user_ids", List.of(myId, userId));
        respond.put("type", 0);

        WebSocketClient.sendMessage(respond.raw());
    }

    private static void updateFriend(int responseType) {
        // Object userId = request.getD().get("user_id");
        // Object type = request.getD().get("type");
    }
}
