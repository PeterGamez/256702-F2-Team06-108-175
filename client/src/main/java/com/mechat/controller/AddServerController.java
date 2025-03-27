package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.AddServerView;

import javafx.event.ActionEvent;

public class AddServerController implements ControllerInterface {

    private AddServerView addServerView = new AddServerView();

    public AddServerController(AddServerView view) {
        addServerView.getDoneButton().setOnAction(this::addServerEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(addServerView);
    }

    private void addServerEvent(ActionEvent e) {

    }

}
