package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.FriendInfoView;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class FriendInfoController implements ControllerInterface {

    private FriendInfoView friendInfoView = new FriendInfoView();

    public FriendInfoController() {
        friendInfoView.getBackButton().setOnAction(this::backEvent);
        friendInfoView.geteditNameButtonLayout().setOnMouseClicked(this::editNameEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(friendInfoView);
    }

    public void backEvent(ActionEvent e) {

    }

    public void editNameEvent(MouseEvent e) {

    }
}
