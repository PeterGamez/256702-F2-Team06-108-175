package com.mechat.controller.navbar;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.navbar.FriendListView;

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
        for (int i = 0; i < 20; i++) {
            friendListView.addChat("", "Friend " + (i + 1));
        }

        ScreenHandler.setScreen(friendListView);
    }
}
