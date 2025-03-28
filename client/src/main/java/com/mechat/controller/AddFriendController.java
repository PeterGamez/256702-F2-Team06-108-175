package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.AddFriendView;

public class AddFriendController extends NavbarController implements ControllerInterface {

    private AddFriendView addFriendView = new AddFriendView();

    public AddFriendController() {
        addFriendView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        addFriendView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        addFriendView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        addFriendView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);

        addFriendView.getSearchedFriendName().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > 0) {
                addFriendView.getImagePath().set("/images/default-profile.png");
                addFriendView.getShowFriendName().set(newVal);
            } else {
                addFriendView.getImagePath().set("");
                addFriendView.getShowFriendName().set("");
            }
        });
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(addFriendView);
    }
}
