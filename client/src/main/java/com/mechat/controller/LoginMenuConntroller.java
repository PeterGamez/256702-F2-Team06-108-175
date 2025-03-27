package com.mechat.controller;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.LoginMenuView;

import javafx.event.ActionEvent;

public class LoginMenuConntroller implements ControllerInterface {

    private LoginMenuView loginMenuView = new LoginMenuView();

    public LoginMenuConntroller() {
        loginMenuView.getBackButton().setOnAction(this::backEvent);
        loginMenuView.getRegisterButton().setOnAction(this::registerEvent);
        loginMenuView.getLoginButton().setOnAction(this::loginEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(loginMenuView);
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(ServerConntroller.class).load();
    }

    private void registerEvent(ActionEvent e) {
        // MakeCache.getController(RegisterMenuController.class).load();
    }

    private void loginEvent(ActionEvent e) {
        // MakeCache.getController(LoginController.class).load();
    }
}
