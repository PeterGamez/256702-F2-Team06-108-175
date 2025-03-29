package com.mechat.controller.home;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.home.LoginMenuView;

import javafx.event.ActionEvent;

public class LoginMenuController implements ControllerInterface {

    private LoginMenuView loginMenuView = new LoginMenuView();

    public LoginMenuController() {
        loginMenuView.getBackButton().setOnAction(this::backEvent);
        loginMenuView.getRegisterButton().setOnAction(this::registerEvent);
        loginMenuView.getLoginButton().setOnAction(this::loginEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(loginMenuView);
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(ServerController.class).load();
    }

    private void registerEvent(ActionEvent e) {
        MakeCache.getController(RegisterController.class).load();
    }

    private void loginEvent(ActionEvent e) {
        MakeCache.getController(LoginController.class).load();
    }
}
