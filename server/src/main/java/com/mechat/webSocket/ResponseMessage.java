package com.mechat.webSocket;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseMessage {
    private int op;
    private int t;
    private Map<String, Object> d = new LinkedHashMap<>();

    public ResponseMessage(int op, int t) {
        this.op = op;
        this.t = t;
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

    public void put(String key, Object value) {
        d.put(key, value);
    }

    public TextMessage send() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseMessage = objectMapper.writeValueAsString(this);

            return new TextMessage(responseMessage);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
