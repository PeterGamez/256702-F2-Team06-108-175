package com.mechat.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseMessage {

    private int op;
    private int t;
    private Map<String, Object> d = new LinkedHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

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

    public String raw() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
