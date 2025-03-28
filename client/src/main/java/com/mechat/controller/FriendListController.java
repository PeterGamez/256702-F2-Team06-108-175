package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.FriendListView;

import javafx.event.ActionEvent;

public class FriendListController extends NavbarController implements ControllerInterface {

    private FriendListView friendListView = new FriendListView();

    public FriendListController() {
        friendListView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        friendListView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        friendListView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        friendListView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(friendListView);
    }

    @Override
    protected void friendListNavbarEvent(ActionEvent e) {
        return;
    }
}
