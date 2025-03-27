package com.mechat.controller;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.RegisterView;

import javafx.event.ActionEvent;

public class RegisterController implements ControllerInterface {

    private  RegisterView registerView = new RegisterView();

    private void RegisterController() {
        registerView.getBackButton().setOnAction(this::backEvent);
        registerView.getRegisterButton().setOnAction(this::registerEvent);
    }

    @Override
    public void load() {

    }

    private void registerEvent(ActionEvent e) {
        String username = registerView.getUsernameProperty().get();
        String password = registerView.getPasswordProperty().get();
        String confirmPassword = registerView.getConfirmPasswordProperty().get();

    }

    private void backEvent(ActionEvent e) {

    }

}
