package com.mechat.webSocket;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ResponseMessage {

    private WebSocketSession session;
    private int op;
    private int t;
    private Map<String, Object> d = new LinkedHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public ResponseMessage(WebSocketSession session, int op, int t) {
        objectMapper.registerModule(new JavaTimeModule());
        
        this.session = session;
        this.op = op;
        this.t = t;
    }

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

    public void send() {
        try {
            String responseMessage = objectMapper.writeValueAsString(this);

            TextMessage textMessage = new TextMessage(responseMessage);
            session.sendMessage(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextMessage json() {
        try {
            String responseMessage = objectMapper.writeValueAsString(this);

            return new TextMessage(responseMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
