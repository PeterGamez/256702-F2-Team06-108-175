package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.GroupMemberView;

import javafx.event.ActionEvent;

public class GroupMemberController implements ControllerInterface {

    private GroupMemberView groupMemberView = new GroupMemberView();

    public GroupMemberController() {
        groupMemberView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(groupMemberView);
    }

    private void backEvent(ActionEvent e) {
    }
}
