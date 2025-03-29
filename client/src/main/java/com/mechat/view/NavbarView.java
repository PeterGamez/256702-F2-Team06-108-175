package com.mechat.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public abstract class NavbarView {

    private HBox navBar;
    protected Button addFriendNavbarButton;
    protected Button friendListNavbarButton;
    protected Button homeNavbarButton;
    protected Button settingNavbarButton;

    public NavbarView() {
        navBar = TemplateView.navBar();

        addFriendNavbarButton = (Button) navBar.getChildren().get(0);
        friendListNavbarButton = (Button) navBar.getChildren().get(1);
        homeNavbarButton = (Button) navBar.getChildren().get(2);
        settingNavbarButton = (Button) navBar.getChildren().get(3);
    }

    protected HBox setNavbar() {
        return navBar;
    }

    public Button getAddFriendNavbarButton() {
        return addFriendNavbarButton;
    }

    public Button getFriendListNavbarButton() {
        return friendListNavbarButton;
    }

    public Button getHomeNavbarButton() {
        return homeNavbarButton;
    }

    public Button getSettingNavbarButton() {
        return settingNavbarButton;
    }
}
