package com.mechat.controller.home;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.controller.navbar.HomeController;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.home.LoginModel;
import com.mechat.service.RestApiService;
import com.mechat.view.home.LoginView;
import com.mechat.websocket.WebSocketClient;

import javafx.event.ActionEvent;

public class LoginController implements ControllerInterface {

    private LoginView loginView = new LoginView();
    private LoginModel loginModel = new LoginModel();

    private ObjectMapper objectMapper = new ObjectMapper();

    public LoginController() {
        loginView.getBackButton().setOnAction(this::backEvent);
        loginView.getLoginButton().setOnAction(this::loginEvent);
    }

    @Override
    public void load() {
        loginView.getUsernameProperty().set(null);
        loginView.getPasswordProperty().set(null);

        loginView.getShowErrorProperty().set(null);

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
            String payload = RestApiService.login(username, password).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            if (respond.get("status").toString().equals("success") == false) {
                String message = Objects.toString(respond.get("message"));
                loginView.getShowErrorProperty().set(message);
                return;
            }

            String accessToken = Objects.toString(respond.get("accessToken"));
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

        WebSocketClient.connect();

        MakeCache.getController(HomeController.class).load();
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(LoginMenuController.class).load();
    }
}
