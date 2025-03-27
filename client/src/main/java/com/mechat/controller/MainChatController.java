package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.ChatView;
import com.mechat.view.MainChatView;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

public class MainChatController implements ControllerInterface {

    private MainChatView mainChatView = new MainChatView();

    public MainChatController() {
        mainChatView.getAddFriendButton().setOnAction(this::addFriendEvent);
        mainChatView.getFriendListButton().setOnAction(this::friendListEvent);
        mainChatView.getHomeButton().setOnAction(this::homeEvent);
        mainChatView.getSettingButton().setOnAction(this::settingEvent);
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

    private void addFriendEvent(ActionEvent e) {

    }

    private void friendListEvent(ActionEvent e) {
    }

    private void homeEvent(ActionEvent e) {

    }

    private void settingEvent(ActionEvent e) {

    }
}
