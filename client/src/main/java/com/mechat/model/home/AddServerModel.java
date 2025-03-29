package com.mechat.model.home;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.utils.AppDataStorage;

public class AddServerModel {

    private ServerModel serverModel = new ServerModel();

    private ObjectMapper objectMapper = new ObjectMapper();

    public void addServer(String serverName, String serverIp, String serverPort) {
        List<Map<String, Object>> serverList = serverModel.getServerList();

        Map<String, Object> newServer = Map.of(
                "serverName", serverName,
                "serverIp", serverIp,
                "serverPort", serverPort);

        serverList.add(newServer);

        try {
            String jsonString = objectMapper.writeValueAsString(serverList);
            AppDataStorage.saveData("serverList.json", jsonString);
        } catch (Exception e) {
        }
    }

}
