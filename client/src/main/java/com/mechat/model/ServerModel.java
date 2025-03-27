package com.mechat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.service.ApiService;

public class ServerModel {

    private ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<Map<String, Object>> getServerList() {
        ArrayList<Map<String, Object>> serverList = new ArrayList<>();

        Map<String, Object> server1 = new HashMap<>();
        server1.put("serverId", 1);
        server1.put("serverName", "Server 1");
        server1.put("serverIp", "127.0.0.1");
        server1.put("serverPort", 8080);
        serverList.add(server1);

        Map<String, Object> server2 = new HashMap<>();
        server2.put("serverId", 2);
        server2.put("serverName", "Server 2");
        server2.put("serverIp", "127.0.0.1");
        server2.put("serverPort", 8081);
        serverList.add(server2);

        Map<String, Object> server3 = new HashMap<>();
        server3.put("serverId", 3);
        server3.put("serverName", "Server 3");
        server3.put("serverIp", "127.0.0.1");
        server3.put("serverPort", 8082);
        serverList.add(server3);

        serverList.forEach(server -> {
            ApiService apiService = new ApiService(server.get("serverIp").toString(), server.get("serverPort").toString());

            try {
                String payload = apiService.getConnection().block();
                JsonNode jsonNode = objectMapper.readTree(payload);

                String message = jsonNode.get("message").asText();

                server.put("isOnline", message.equals("Online"));
            } catch (Exception e) {
                server.put("isOnline", false);
            }
        });

        return serverList;
    }
}
