package com.mechat.controller;

import com.mechat.controller.navbar.AddFriendController;
import com.mechat.controller.navbar.FriendListController;
import com.mechat.controller.navbar.HomeController;
import com.mechat.controller.navbar.SettingController;
import com.mechat.utils.MakeCache;

import javafx.event.ActionEvent;

public abstract class NavbarController {

    protected void addFriendNavbarEvent(ActionEvent e) {
        MakeCache.getController(AddFriendController.class).load();
    }

    protected void friendListNavbarEvent(ActionEvent e) {
        MakeCache.getController(FriendListController.class).load();
    }

    protected void homeNavbarEvent(ActionEvent e) {
        MakeCache.getController(HomeController.class).load();
    }

    protected void settingNavbarEvent(ActionEvent e) {
        MakeCache.getController(SettingController.class).load();
    }
}
