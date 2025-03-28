package com.mechat.controller;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.SettingView;

import javafx.event.ActionEvent;

public class SettingController extends NavbarController implements ControllerInterface {

    private SettingView settingView = new SettingView();

    public SettingController() {
        settingView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        settingView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        settingView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        settingView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);

        settingView.getGeneralButton().setOnAction(this::generalEvent);
        settingView.getAboutButton().setOnAction(this::aboutEvent);
        settingView.getLogOutButton().setOnAction(this::logOutEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(settingView);
    }

    private void generalEvent(ActionEvent e) {
    }

    private void aboutEvent(ActionEvent e) {
    }

    private void logOutEvent(ActionEvent e) {
        MakeCache.setAuthToken(null);
        MakeCache.getController(LoginController.class).load();
    }
}
