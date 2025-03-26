package com.mechat.view;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ScreenInterface;

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

public class ServerView implements ScreenInterface {

    private String ip;
    private String port;

    @Override
    public Parent createContent() {
        Label header = new Label("Select Server");
        header.getStyleClass().add("misc-label");

        // ListView for Servers
        ListView<HBox> serverListView = new ListView<>();
        serverListView.getItems().addAll(
                createServerItem("Server 1", ip + ":" + port, false),
                createServerItem("Server 2", ip + ":" + port, false)
        );

        // Buttons
        Button joinButton = createButton("Join");
        Button addServerButton = createButton("Add Server");
        addServerButton.setOnAction(e -> ScreenHandler.setScreen(new EditServerView()));
        Button deleteButton = createButton("Delete");
        deleteButton.setOnAction(e -> {
            int selectedIndex = serverListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                serverListView.getItems().remove(selectedIndex);
            }
        });
        Button backButton = createButton("Back");
        backButton.setOnAction(e -> ScreenHandler.setScreen(new MainView()));

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

    private HBox createServerItem(String serverName, String serverInfo, boolean isOnline) {
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

    private Button createButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("server-button");
        return button;
    }
}
