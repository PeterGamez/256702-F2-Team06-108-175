package com.mechat.utils;

import com.mechat.MakeCache;
import com.mechat.controller.AddFriendController;
import com.mechat.controller.FriendListController;
import com.mechat.controller.MainChatController;
import com.mechat.controller.SettingController;

import javafx.event.ActionEvent;

public abstract class NavbarController {

    protected void addFriendNavbarEvent(ActionEvent e) {
        MakeCache.getController(AddFriendController.class).load();
    }

    protected void friendListNavbarEvent(ActionEvent e) {
        MakeCache.getController(FriendListController.class).load();
    }

    protected void homeNavbarEvent(ActionEvent e) {
        MakeCache.getController(MainChatController.class).load();
    }

    protected void settingNavbarEvent(ActionEvent e) {
        MakeCache.getController(SettingController.class).load();
    }
}
