package com.mechat.utils;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public abstract class NavbarView {

    protected Button addFriendButton;
    protected Button friendListButton;
    protected Button homeButton;
    protected Button settingButton;

    protected HBox setNavbar() {
        HBox navBar = TemplateView.navBar();

        addFriendButton = (Button) navBar.getChildren().get(0);
        friendListButton = (Button) navBar.getChildren().get(1);
        homeButton = (Button) navBar.getChildren().get(2);
        settingButton = (Button) navBar.getChildren().get(3);

        return navBar;
    }

    public Button getAddFriendButton() {
        return addFriendButton;
    }

    public Button getFriendListButton() {
        return friendListButton;
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public Button getSettingButton() {
        return settingButton;
    }
}
