package com.mechat.controller.home;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.RestApiService;
import com.mechat.view.home.RegisterView;

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
        registerView.getUsernameProperty().set(null);
        registerView.getPasswordProperty().set(null);

        registerView.getShowErrorProperty().set(null);

        ScreenHandler.setScreen(registerView);
    }

    private void registerEvent(ActionEvent e) {
        String username = registerView.getUsernameProperty().get();
        String password = registerView.getPasswordProperty().get();
        String confirmPassword = registerView.getConfirmPasswordProperty().get();

        registerView.getPasswordProperty().set(null);
        registerView.getConfirmPasswordProperty().set(null);

        if (username == null || password == null || confirmPassword == null) {
            registerView.getShowErrorProperty().set("Please fill all fields");
            return;
        }

        if (password.equals(confirmPassword) == false) {
            registerView.getShowErrorProperty().set("Password and confirm password must be the same");
            return;
        }

        try {
            String payload = RestApiService.register(username, password).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            if (respond.get("status").toString().equals("success") == false) {
                String message = Objects.toString(respond.get("message"));
                registerView.getShowErrorProperty().set(message);
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            registerView.getShowErrorProperty().set("Server error");
            return;
        }

        registerView.getShowErrorProperty().set(null);
        registerView.getUsernameProperty().set(null);

        MakeCache.getController(LoginController.class).load();
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(LoginMenuController.class).load();
    }

}
