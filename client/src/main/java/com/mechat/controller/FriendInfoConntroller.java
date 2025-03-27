package com.mechat.controller;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.FriendInfoView;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class FriendInfoConntroller implements ControllerInterface {

    FriendInfoView friendInfoView = new FriendInfoView();

    public FriendInfoConntroller() {
        friendInfoView.getBackButton().setOnAction(this::backEvent);
        friendInfoView.geteditNameButtonLayout().setOnMouseClicked(this::editNameEvent);
    }

    @Override
    public void load() {

    }

    public void backEvent(ActionEvent e) {

    }

    public void editNameEvent(MouseEvent e) {

    }
}
