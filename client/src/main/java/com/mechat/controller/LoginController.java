package com.mechat.controller;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.LoginView;

import javafx.event.ActionEvent;

public class LoginController implements ControllerInterface {

    private LoginView loginView = new LoginView();

    public void LoginController() {
        loginView.getBackButton().setOnAction(this::backEvent);
        loginView.getLoginButton().setOnAction(this::loginEvent);
    }

    @Override
    public void load() {

    }

    private void loginEvent(ActionEvent e) {
        String username = loginView.getUsernameProperty().get();
        String password = loginView.getPasswordProperty().get();
    }

    private void backEvent(ActionEvent e) {

    }
}
