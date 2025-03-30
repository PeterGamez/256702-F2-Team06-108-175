package com.mechat.model.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.service.RestApiService;
import com.mechat.utils.AppDataStorage;

public class ServerModel {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> getServerStatus() {
        List<Map<String, Object>> serverList = getServerList();

        serverList.forEach(server -> {
            try {
                String serverIp = Objects.toString(server.get("serverIp"));
                String serverPort = Objects.toString(server.get("serverPort"));

                String payload = RestApiService.getConnection(serverIp, serverPort).block();

                if (payload != null) {
                    JsonNode jsonNode = objectMapper.readTree(payload);
                    Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
                    });
                    boolean isOnline = respond.get("message").toString().equals("Online");

                    server.put("isOnline", isOnline);
                } else {
                    server.put("isOnline", false);
                }
            } catch (Exception e) {
                server.put("isOnline", false);
            }
        });

        return serverList;
    }

    public List<Map<String, Object>> getServerList() {
        List<Map<String, Object>> serverList = new ArrayList<>();

        String appDataStorage = AppDataStorage.loadData("serverList.json");
        if (appDataStorage == null) {
            AppDataStorage.saveData("serverList.json", "[]");
            appDataStorage = AppDataStorage.loadData("serverList.json");
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(appDataStorage);
            serverList = objectMapper.convertValue(jsonNode, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
        }

        return serverList;
    }

    public void deleteServer(int index) {
        List<Map<String, Object>> serverList = getServerList();
        serverList.remove(index);

        try {
            String jsonString = objectMapper.writeValueAsString(serverList);
            AppDataStorage.saveData("serverList.json", jsonString);
        } catch (Exception e) {
        }
    }
}
