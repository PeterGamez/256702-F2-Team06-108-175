package com.mechat.screens;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SettingScreen implements ScreenInterface {

    private String user = "USER";
    private String uid = "0000000001";

    @Override
    public Parent createContent() {
        // Header over Menu
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setPrefHeight(70);

        ImageView backButton = new ImageView(new Image("images/back-button.png"));
        backButton.setFitWidth(20);
        backButton.setFitHeight(20);

        Label settingsLabel = new Label("Settings");
        settingsLabel.getStyleClass().add("settings-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(backButton, settingsLabel, spacer);
        header.setSpacing(30);

        // Menu
        VBox menu = new VBox();

        Button generalButton = new Button("General");
        Button chatButton = new Button("Chat");
        Button aboutButton = new Button("About");

        generalButton.getStyleClass().add("left-menu");
        chatButton.getStyleClass().add("left-menu");
        aboutButton.getStyleClass().add("left-menu");

        menu.getChildren().addAll(header, generalButton, chatButton, aboutButton);
        menu.setStyle("-fx-background-color: #545454;");
        VBox.setVgrow(menu, Priority.ALWAYS);

        // Content
        VBox content = new VBox();
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(40);
        content.setPadding(new Insets(100, 0, 0, 0));

        ImageView avatar = new ImageView(new Image("images/profile-icon.png"));
        avatar.setFitWidth(100);
        avatar.setFitHeight(100);

        // HBox profileBox = new HBox();
        // profileBox.setPadding(new Insets(0, 100, 0, 100));
        // Label title = new Label("Display Name");
        // title.getStyleClass().add("settings-label");
        // Label name = new Label(user);
        // name.getStyleClass().add("settings-label");
        // profileBox.getChildren().addAll(title, name);
        // profileBox.setAlignment(Pos.CENTER);
        // profileBox.setSpacing(60);
        // HBox UUIDBox = new HBox();
        // UUIDBox.setPadding(new Insets(0, 100, 0, 100));
        // Label uTitle = new Label("UUID");
        // uTitle.getStyleClass().add("settings-label");
        // Label uuid = new Label(uid);
        // uuid.getStyleClass().add("settings-label");
        // UUIDBox.getChildren().addAll(uTitle, uuid);
        // UUIDBox.setAlignment(Pos.CENTER);
        // UUIDBox.setSpacing(60);

        // content.getChildren().addAll(avatar, profileBox, UUIDBox);

        VBox titleBox = new VBox();
        titleBox.setSpacing(40);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        Label userTitle = new Label("Display User");
        userTitle.getStyleClass().add("settings-label");
        Label UUIDTitle = new Label("UUID");
        UUIDTitle.getStyleClass().add("settings-label");
        titleBox.getChildren().addAll(userTitle, UUIDTitle);

        VBox informationBox = new VBox();
        informationBox.setSpacing(40);
        informationBox.setAlignment(Pos.CENTER_RIGHT);
        Label userLabel = new Label(user);
        userLabel.getStyleClass().add("settings-label");
        Label UUIDLabel = new Label(uid);
        UUIDLabel.getStyleClass().add("settings-label");
        informationBox.getChildren().addAll(userLabel, UUIDLabel);

        HBox mergedBox = new HBox();
        mergedBox.setAlignment(Pos.CENTER);
        mergedBox.setSpacing(40);
        mergedBox.getChildren().addAll(titleBox, informationBox);

        content.getChildren().addAll(avatar, mergedBox);
        BorderPane root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(content);

        return root;
    }
}
