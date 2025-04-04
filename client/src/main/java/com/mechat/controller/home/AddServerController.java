package com.mechat.controller.home;

import java.net.InetAddress;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.home.AddServerModel;
import com.mechat.utils.HostWithPortValidator;
import com.mechat.utils.MakeCache;
import com.mechat.utils.ScreenHandler;
import com.mechat.view.home.AddServerView;

import javafx.event.ActionEvent;

public class AddServerController implements ControllerInterface {

    private AddServerView addServerView = new AddServerView();
    private AddServerModel addServerModel = new AddServerModel();

    public AddServerController() {
        addServerView.getDoneButton().setOnAction(this::doneEvent);
        addServerView.getCancelButton().setOnAction(this::cancelEvent);
    }

    @Override
    public void load() {
        addServerView.getServerNameProperty().set(null);
        addServerView.getServerAddressProperty().set(null);

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

        if (!HostWithPortValidator.isValidHostWithPort(serverAddress)) {
            addServerView.getShowErrorProperty().set("Invalid server address format ip:port or domain:port");
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

        addServerModel.addServer(serverName, serverIp, serverPort);

        MakeCache.getController(ServerController.class).load();
    }

    private void cancelEvent(ActionEvent e) {
        MakeCache.getController(ServerController.class).load();
    }

}
