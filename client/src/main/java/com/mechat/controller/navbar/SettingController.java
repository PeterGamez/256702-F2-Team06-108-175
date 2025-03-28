package com.mechat.controller.navbar;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.controller.home.LoginController;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.WebSocketClient;
import com.mechat.utils.NavbarController;
import com.mechat.view.navbar.SettingView;

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

        String uuid = String.valueOf(MakeCache.getUser().get("id"));
        String username = String.valueOf(MakeCache.getUser().get("username"));

        settingView.getUuidProperty().set(uuid);
        settingView.getUserProperty().set(username);
    }

    private void generalEvent(ActionEvent e) {
        this.load();
    }

    private void aboutEvent(ActionEvent e) {
        ScreenHandler.setScreen(settingView.createAboutContent());
    }

    private void logOutEvent(ActionEvent e) {
        MakeCache.setAuthToken(null);
        MakeCache.getController(LoginController.class).load();
        WebSocketClient.close();
    }
}
