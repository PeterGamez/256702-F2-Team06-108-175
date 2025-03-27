package com.mechat.controller;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.AddServerView;

import javafx.event.ActionEvent;

public class AddServerConntroller implements ControllerInterface {

    private AddServerView addServerView = new AddServerView();

    public AddServerConntroller(AddServerView view) {
        addServerView.getDoneButton().setOnAction(this::addServerEvent);
    }

    @Override
    public void load() {

    }

    private void addServerEvent(ActionEvent e) {

    }

}
