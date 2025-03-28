package com.mechat.controller.home;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.ServerModel;
import com.mechat.view.home.AddServerView;

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
        addServerView.getShowErrorProperty().set(null);
        ScreenHandler.setScreen(addServerView);
    }

    private void doneEvent(ActionEvent e) {
        String serverName = addServerView.getServerNameProperty().get();
        String serverAddress = addServerView.getServerAddressProperty().get();

        if (serverName == null || serverAddress == null) {
            addServerView.getShowErrorProperty().set("Please fill all fields");
            return;
        }

        String serverIp;
        String serverPort;

        try {
            String[] serverAddressParts = serverAddress.split(":");
            serverIp = serverAddressParts[0];
            serverPort = serverAddressParts[1];
        } catch (Exception ex) {
            addServerView.getShowErrorProperty().set("Invalid server address format");
            return;
        }

        if (serverIp == null || serverPort == null) {
            addServerView.getShowErrorProperty().set("Invalid server IP or Port");
            return;
        }

        addServerView.getShowErrorProperty().set(null);
        serverModel.addServer(serverName, serverIp, serverPort);

        MakeCache.getController(ServerController.class).load();
    }

    private void cancelEvent(ActionEvent e) {
        MakeCache.getController(ServerController.class).load();
    }

}
