package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.AddFriendView;

import javafx.event.ActionEvent;

public class AddFriendController implements ControllerInterface {

    private AddFriendView addFriendView = new AddFriendView();

    public AddFriendController() {
        addFriendView.getAddFriendButton().setOnAction(this::addFriendEvent);
        addFriendView.getFriendListButton().setOnAction(this::friendListEvent);
        addFriendView.getHomeButton().setOnAction(this::homeEvent);
        addFriendView.getSettingButton().setOnAction(this::settingEvent);

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

    private void addFriendEvent(ActionEvent e) {

    }

    private void friendListEvent(ActionEvent e) {

    }

    private void homeEvent(ActionEvent e) {

    }

    private void settingEvent(ActionEvent e) {

    }

}
