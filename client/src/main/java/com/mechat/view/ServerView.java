package com.mechat.view;

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

    @Override
    public Parent createContent() {
        Label header = new Label("Select Server");
        header.getStyleClass().add("misc-label");

        // ListView for Servers
        ListView<HBox> serverListView = new ListView<>();
        serverListView.getItems().addAll(
                createServerItem("Server 1", true),
                createServerItem("Server 2", false)
        );

        // Buttons
        Button joinButton = createButton("Join");
        Button addServerButton = createButton("Add Server");
        Button deleteButton = createButton("Delete");
        Button backButton = createButton("Back");

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(joinButton, addServerButton, deleteButton, backButton);
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

    // Helper method to create an HBox for a server item
    private HBox createServerItem(String serverName, boolean isOnline) {
        Label nameLabel = new Label(serverName);
        Label statusLabel = new Label(isOnline ? "● Online" : "● Offline");
        statusLabel.setStyle(isOnline ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox hbox = new HBox(nameLabel, spacer, statusLabel);
        hbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }

    // Helper method to create a button with a specific style class
    private Button createButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("server-button");
        return button;
    }
}
