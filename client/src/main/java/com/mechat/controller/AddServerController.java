package com.mechat.controller;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.ServerModel;
import com.mechat.view.AddServerView;
import javafx.event.ActionEvent;

public class AddServerController implements ControllerInterface {

    private AddServerView addServerView = new AddServerView();
    private ServerModel serverModel = new ServerModel();

    public AddServerController() {
        addServerView.getDoneButton().setOnAction(this::doneEvent);
        addServerView.getCancelButton().setOnAction(this::cancelEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(addServerView);
    }

    private void doneEvent(ActionEvent e) {
        String serverName = addServerView.getServerNameProperty().get();
        String serverAddress = addServerView.getServerAddressProperty().get();

        if (serverName == null || serverAddress == null) {
            return;
        }

        String serverIp;
        String serverPort;

        try {
            String[] serverAddressParts = serverAddress.split(":");
            serverIp = serverAddressParts[0];
            serverPort = serverAddressParts[1];
        } catch (Exception ex) {
            return;
        }

        if (serverIp == null || serverPort == null) {
            return;
        }

        serverModel.addServer(serverName, serverIp, serverPort);

        MakeCache.getController(ServerController.class).load();
    }

    private void cancelEvent(ActionEvent e) {
        MakeCache.getController(ServerController.class).load();
    }

}
