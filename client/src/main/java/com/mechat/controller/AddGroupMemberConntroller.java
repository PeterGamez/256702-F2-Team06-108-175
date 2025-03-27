package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.AddGroupMemberView;

import javafx.event.ActionEvent;

public class AddGroupMemberConntroller implements ControllerInterface {

    AddGroupMemberView addGroupMemberView = new AddGroupMemberView();

    public AddGroupMemberConntroller() {
        addGroupMemberView.getAddGroupMemberButton().setOnAction(this::addMemberEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(addGroupMemberView);
    }

    private void addMemberEvent(ActionEvent e) {

    }

    private void backEvent(ActionEvent e) {

    }
}
