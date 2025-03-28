package com.mechat.controller.navbar;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.controller.chat.ChatController;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.navbar.HomeView;

import javafx.scene.layout.Pane;

public class HomeController extends NavbarController implements ControllerInterface {

    private HomeView homeView = new HomeView();

    public HomeController() {
        homeView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        homeView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        homeView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        homeView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);
    }

    @Override
    public void load() {
        homeView.getChats().clear();

        for (int i = 0; i < 20; i++) {
            homeView.addChat("", "Friend " + (i + 1));
        }

        for (Pane chat : homeView.getChats()) {
            chat.setOnMouseClicked(e -> {
                MakeCache.getController(ChatController.class).load();
            });
        }

        ScreenHandler.setScreen(homeView);

        String uuid = String.valueOf(MakeCache.getUser().get("id"));
        String username = String.valueOf(MakeCache.getUser().get("username"));

        homeView.getUuidProperty().set("UUID: " + uuid);
        homeView.getUserProperty().set(username);
    }
}
