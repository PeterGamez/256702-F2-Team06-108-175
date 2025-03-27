package com.mechat.controller;

import java.util.ArrayList;
import java.util.Map;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.ServerModel;
import com.mechat.view.ServerView;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class ServerConntroller implements ControllerInterface {

    ServerView serverView = new ServerView();
    ServerModel serverModel = new ServerModel();
    ArrayList<Map<String, Object>> serverList;

    public ServerConntroller() {
        serverView.getJoinButton().setOnAction(this::joinEvent);
        serverView.getAddServerButton().setOnAction(this::addServerEvent);
        serverView.getDeleteButton().setOnAction(this::deleteEvent);
        serverView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        serverView.getServerListView().getItems().clear();
        ScreenHandler.setScreen(serverView);

        serverList = serverModel.getServerList();
        serverList.forEach(s -> {
            serverView.getServerListView().getItems()
                    .add(serverView.createServerItem(s.get("serverName").toString(), s.get("serverIp").toString() + ":" + s.get("serverPort"), (boolean) s.get("isOnline")));
        });
    }

    private void joinEvent(ActionEvent e) {
        ListView<?> listView = serverView.getServerListView();
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        Map<String, Object> selectedServer = serverList.get(selectedIndex);

        System.out.println(selectedServer);
        if (selectedServer.get("isOnline").equals(false)) {
            return;
        }

        MakeCache.setServer(selectedServer);

        MakeCache.getController(LoginMenuConntroller.class).load();
    }

    private void addServerEvent(ActionEvent e) {
        // ScreenHandler.setScreen(new AddServerView());
    }

    private void deleteEvent(ActionEvent e) {
        // serverView.deleteServer(e);
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(MainController.class).load();
    }
}
