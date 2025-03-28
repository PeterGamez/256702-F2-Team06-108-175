package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.ChatView;
import com.mechat.view.MainChatView;

import javafx.scene.layout.Pane;

public class MainChatController extends NavbarController implements ControllerInterface {

    private MainChatView mainChatView = new MainChatView();

    public MainChatController() {
        mainChatView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        mainChatView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        mainChatView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        mainChatView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(mainChatView);

        for (int i = 0; i < 20; i++) {
            mainChatView.addChat("", "Friend " + (i + 1));
        }

        for (Pane chat : mainChatView.getChats()) {
            chat.setOnMouseClicked(e -> {
                ScreenHandler.setScreen(new ChatView());
            });
        }
    }
}
