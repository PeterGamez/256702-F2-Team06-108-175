package com.mechat.controller;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.ApiService;
import com.mechat.view.RegisterView;

import javafx.event.ActionEvent;

public class RegisterController implements ControllerInterface {

    private RegisterView registerView = new RegisterView();

    private ObjectMapper objectMapper = new ObjectMapper();

    public RegisterController() {
        registerView.getBackButton().setOnAction(this::backEvent);
        registerView.getRegisterButton().setOnAction(this::registerEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(registerView);
    }

    private void registerEvent(ActionEvent e) {
        String username = registerView.getUsernameProperty().get();
        String password = registerView.getPasswordProperty().get();
        String confirmPassword = registerView.getConfirmPasswordProperty().get();

        registerView.getPasswordProperty().set(null);
        registerView.getConfirmPasswordProperty().set(null);

        if (username == null || password == null || confirmPassword == null) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            return;
        }

        try {
            String serverIp = String.valueOf(MakeCache.getServer().get("serverIp"));
            String serverPort = String.valueOf(MakeCache.getServer().get("serverPort"));

            ApiService apiService = new ApiService(serverIp, serverPort);

            String payload = apiService.register(username, password).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            if (respond.get("status").toString().equals("success") == false) {
                return;
            }

            String token = String.valueOf(respond.get("token"));

            MakeCache.setAuthToken(token);

        } catch (Exception ex) {
            return;
        }

        registerView.getUsernameProperty().set(null);

        MakeCache.getController(LoginController.class).load();
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(LoginMenuController.class).load();
    }

}
