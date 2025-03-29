package com.mechat.view.home;

import com.mechat.interfaces.ViewInterface;
import com.mechat.view.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ServerView implements ViewInterface {

    private Button joinButton;
    private Button addServerButton;
    private Button deleteButton;
    private Button backButton;
    private ListView<HBox> serverListView = new ListView<>();

    public ServerView() {
        joinButton = TemplateView.createButton("Join", "button");
        addServerButton = TemplateView.createButton("Add Server", "button");
        deleteButton = TemplateView.createButton("Delete", "button");
        backButton = TemplateView.createButton("Back", "button");
    }

    @Override
    public Parent createContent() {
        Label header = new Label("Select Server");
        header.getStyleClass().add("misc-label");

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(backButton, addServerButton, deleteButton, joinButton);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);

        // Layout
        VBox root = new VBox();
        root.getChildren().addAll(header, serverListView, buttonBox);
        root.setSpacing(20);
        root.setPadding(new Insets(20, 100, 20, 100));
        root.setAlignment(Pos.CENTER);

        return root;
    }

    public HBox createServerItem(String serverName, String serverInfo, boolean isOnline) {
        Label nameLabel = new Label(serverName);

        Label serverInfoLabel = new Label(serverInfo);

        Label statusLabel = new Label(isOnline ? "● Online" : "● Offline");
        statusLabel.setStyle(isOnline ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox hbox = new HBox(nameLabel, serverInfoLabel, spacer, statusLabel);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(0, 10, 0, 10));
        hbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }

    public Button getJoinButton() {
        return joinButton;
    }

    public Button getAddServerButton() {
        return addServerButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public ListView<HBox> getServerListView() {
        return serverListView;
    }
}
