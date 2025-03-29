package com.mechat.webSocket;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestMessage {

    private int op;
    private int t;
    private Map<String, Object> d = new LinkedHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public RequestMessage(String payload) {
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);

            this.op = jsonNode.get("op").asInt();
            this.t = jsonNode.get("t").asInt();

            if (jsonNode.has("d") && jsonNode.get("d").isObject()) {
                this.d = objectMapper.convertValue(jsonNode.get("d"), new TypeReference<Map<String, Object>>() {
                });
            }
        } catch (JsonProcessingException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int getOp() {
        return op;
    }

    public int getT() {
        return t;
    }

    public Map<String, Object> getD() {
        return d;
    }
}
