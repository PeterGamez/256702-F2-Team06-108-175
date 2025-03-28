package com.mechat.controller;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.ApiService;
import com.mechat.view.LoginView;

import javafx.event.ActionEvent;

public class LoginController implements ControllerInterface {

    private LoginView loginView = new LoginView();

    private ObjectMapper objectMapper = new ObjectMapper();

    public LoginController() {
        loginView.getBackButton().setOnAction(this::backEvent);
        loginView.getLoginButton().setOnAction(this::loginEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(loginView);
    }

    private void loginEvent(ActionEvent e) {
        String username = loginView.getUsernameProperty().get();
        String password = loginView.getPasswordProperty().get();

        loginView.getPasswordProperty().set(null);

        if (username == null || password == null) {
            loginView.getShowErrorProperty().set("Please fill all fields");
            return;
        }

        try {
            String serverIp = String.valueOf(MakeCache.getServer().get("serverIp"));
            String serverPort = String.valueOf(MakeCache.getServer().get("serverPort"));

            ApiService apiService = new ApiService(serverIp, serverPort);

            String payload = apiService.login(username, password).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            if (respond.get("status").toString().equals("success") == false) {
                loginView.getShowErrorProperty().set(respond.get("message").toString());
                return;
            }

            String accessToken = String.valueOf(respond.get("accessToken"));
            if (accessToken == null) {
                loginView.getShowErrorProperty().set("Server error");
                return;
            }

            MakeCache.setAuthToken(accessToken);

        } catch (Exception ex) {
            loginView.getShowErrorProperty().set("Server error");
            return;
        }

        loginView.getShowErrorProperty().set(null);
        loginView.getUsernameProperty().set(null);

        MakeCache.getController(MainChatController.class).load();
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(LoginMenuController.class).load();
    }
}
