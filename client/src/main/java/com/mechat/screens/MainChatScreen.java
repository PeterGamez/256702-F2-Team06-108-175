package com.mechat.screens;

import java.util.ArrayList;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainChatScreen implements ScreenInterface {

    private ArrayList<Pane> chats = new ArrayList<>();

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        VBox leftBox = new VBox();
        leftBox.getStyleClass().add("left-box");

        VBox userBox = new VBox();
        userBox.getStyleClass().add("user-box");
        userBox.getChildren().add(userInfo());
        userBox.setAlignment(Pos.CENTER);

        VBox friendList = new VBox();

        for (int i = 0; i < 99; i++) {
            addChat("", "Friend " + (i + 1));
        }

        friendList.getChildren().addAll(chats);

        ScrollPane scrollPane = new ScrollPane(friendList);
        scrollPane.getStyleClass().add("scroll-pane");

        leftBox.getChildren().addAll(userBox, scrollPane);
        root.setLeft(leftBox);
        return root;
    }

    public void addChat(String avatar, String title) {
        VBox chat = new VBox();
        chat.setAlignment(Pos.CENTER);

        Label label = new Label(title);
        label.getStyleClass().add("friend-label");

        ImageView profile = new ImageView("images/profile-icon.png");
        profile.setFitHeight(50);
        profile.setFitWidth(50);

        HBox chatHeader = new HBox();
        HBox.setMargin(profile, new Insets(0, 0, 0, 20));
        chatHeader.getChildren().addAll(profile, label);
        chatHeader.setSpacing(20);
        chatHeader.setAlignment(Pos.CENTER_LEFT);

        chat.getChildren().add(chatHeader);
        chat.getStyleClass().add("friend-box");

        chats.add(chat);

    }

    public Parent userInfo() {
        ImageView profile = new ImageView("images/profile-icon.png");
        profile.setFitHeight(80);
        profile.setFitWidth(80);

        Label userLabel = new Label("User");
        userLabel.getStyleClass().add("user-label");
        Label uidLabel = new Label("UID: 1234567890");
        uidLabel.getStyleClass().add("uid-label");

        VBox userInfo = new VBox();
        userInfo.getChildren().addAll(userLabel, uidLabel);
        userInfo.setSpacing(5);
        userInfo.setAlignment(Pos.CENTER_LEFT);

        HBox userBox = new HBox();
        userBox.getChildren().addAll(profile, userInfo);
        userBox.setSpacing(20);
        userBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(profile, new Insets(0, 0, 0, 20));

        return userBox;
    }
}
