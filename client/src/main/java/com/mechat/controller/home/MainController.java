package com.mechat.controller.home;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.home.MainModel;
import com.mechat.utils.MakeCache;
import com.mechat.utils.ScreenHandler;
import com.mechat.view.home.MainView;

import javafx.event.ActionEvent;

public class MainController implements ControllerInterface {

    private MainView mainView = new MainView();
    private MainModel mainModel = new MainModel();

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
