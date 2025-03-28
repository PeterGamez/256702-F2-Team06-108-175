package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.AddFriendView;

public class AddFriendController extends NavbarController implements ControllerInterface {

    private AddFriendView addFriendView = new AddFriendView();

    public AddFriendController() {
        // Set up navbar button actions
        addFriendView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        addFriendView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        addFriendView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        addFriendView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);

        addFriendView.getSearchedFriendName().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                addFriendView.getImagePath().set("");
                addFriendView.getShowFriendName().set("");
                addFriendView.getShowErrorProperty().set("Invalid friend name");
                addFriendView.getAddFriendButton().setVisible(false);
            } else {
                addFriendView.getImagePath().set("/images/default-profile.png");
                addFriendView.getShowFriendName().set(newVal);
                addFriendView.getShowErrorProperty().set("");
                addFriendView.getAddFriendButton().setVisible(true);
            }
        });
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(addFriendView);
    }
}