package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.FriendListView;

import javafx.event.ActionEvent;

public class FriendListController implements ControllerInterface {

    private FriendListView friendListView = new FriendListView();

    public FriendListController() {
        friendListView.getAddFriendButton().setOnAction(this::addFriendEvent);
        friendListView.getFriendListButton().setOnAction(this::friendListEvent);
        friendListView.getHomeButton().setOnAction(this::homeEvent);
        friendListView.getSettingButton().setOnAction(this::settingEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(friendListView);
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
