package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.GroupInfoView;

import javafx.event.ActionEvent;

public class GroupInfoController implements ControllerInterface {

    private GroupInfoView groupInfoView = new GroupInfoView();

    public GroupInfoController() {
        groupInfoView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(groupInfoView);
    }
    
    private void backEvent(ActionEvent e) {
    }
}
