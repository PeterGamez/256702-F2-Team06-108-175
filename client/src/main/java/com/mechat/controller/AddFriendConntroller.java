package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.AddFriendView;

import javafx.event.ActionEvent;

public class AddFriendConntroller implements ControllerInterface {

    AddFriendView addFriendView = new AddFriendView();

    public AddFriendConntroller() {
        addFriendView.getAddFriendButton().setOnAction(this::addFriendEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(addFriendView);

    }

    private void addFriendEvent(ActionEvent e) {

    }

}
