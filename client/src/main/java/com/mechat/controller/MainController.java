package com.mechat.controller;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.MainView;

import javafx.event.ActionEvent;

public class MainController implements ControllerInterface {

    private MainView mainView = new MainView();

    public MainController() {
        mainView.getServerButton().setOnAction(this::serverEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(mainView);
    }

    private void serverEvent(ActionEvent e) {
        MakeCache.getController(ServerController.class).load();
    }
}
