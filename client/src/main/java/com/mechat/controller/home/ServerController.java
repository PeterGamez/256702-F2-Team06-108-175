package com.mechat.controller.home;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.home.ServerModel;
import com.mechat.service.RestApiService;
import com.mechat.utils.MakeCache;
import com.mechat.utils.ScreenHandler;
import com.mechat.view.home.ServerView;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class ServerController implements ControllerInterface {

    private ServerView serverView = new ServerView();
    private ServerModel serverModel = new ServerModel();

    private List<Map<String, Object>> serverList;

    public ServerController() {
        serverView.getJoinButton().setOnAction(this::joinEvent);
        serverView.getAddServerButton().setOnAction(this::addServerEvent);
        serverView.getDeleteButton().setOnAction(this::deleteEvent);
        serverView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        MakeCache.setServer(null);
        RestApiService.disconnect();

        serverView.getServerListView().getItems().clear();

        ScreenHandler.setScreen(serverView);

        serverList = serverModel.getServerStatus();
        serverList.stream().forEach(s -> {
            String serverName = Objects.toString(s.get("serverName"));
            String serverIp = Objects.toString(s.get("serverIp"));
            String serverPort = Objects.toString(s.get("serverPort"));
            boolean isOnline = (boolean) s.get("isOnline");

            serverView.getServerListView().getItems()
                    .add(serverView.createServerItem(serverName, serverIp + ":" + serverPort, isOnline));
        });

        serverView.getServerListView().getSelectionModel().select(0);
    }

    private void joinEvent(ActionEvent e) {
        ListView<?> listView = serverView.getServerListView();
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        Map<String, Object> selectedServer = serverList.get(selectedIndex);

        if (selectedServer.get("isOnline").equals(false)) {
            return;
        }

        MakeCache.setServer(selectedServer);
        RestApiService.connect();

        MakeCache.getController(LoginMenuController.class).load();
    }

    private void addServerEvent(ActionEvent e) {
        MakeCache.getController(AddServerController.class).load();
    }

    private void deleteEvent(ActionEvent e) {
        ListView<?> listView = serverView.getServerListView();
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        serverModel.deleteServer(selectedIndex);

        serverView.getServerListView().getItems().remove(selectedIndex);
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(MainController.class).load();
    }
}
