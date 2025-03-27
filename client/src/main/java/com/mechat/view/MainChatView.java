package com.mechat.view;

import java.util.ArrayList;

import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainChatView implements ViewInterface {

    private Button addFriendButton;
    private Button friendListButton;
    private Button homeButton;
    private Button settingButton;

    private ArrayList<Pane> chats = new ArrayList<>();

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();
        root.setLeft(leftBar());
        root.setCenter(chatBox());

        HBox navBar = TemplateView.navBar();

        addFriendButton = (Button) navBar.getChildren().get(0);
        friendListButton = (Button) navBar.getChildren().get(1);
        homeButton = (Button) navBar.getChildren().get(2);
        settingButton = (Button) navBar.getChildren().get(3);

        root.setBottom(navBar);

        return root;
    }

    public void addChat(String avatar, String title) {
        VBox chat = new VBox();
        chat.setAlignment(Pos.CENTER);

        Label label = new Label(title);
        label.getStyleClass().add("friend-label");

        ImageView profile = TemplateView.createImageView("/images/profile-icon.png", 50, 50);

        HBox chatHeader = new HBox();
        HBox.setMargin(profile, new Insets(0, 0, 0, 20));
        chatHeader.getChildren().addAll(profile, label);
        chatHeader.setSpacing(20);
        chatHeader.setAlignment(Pos.CENTER_LEFT);

        chat.getChildren().add(chatHeader);
        chat.getStyleClass().add("friend-box");

        chats.add(chat);
    }

    private Parent userInfo() {
        ImageView profile = TemplateView.createImageView("/images/profile-icon.png", 80, 80);

        Label userLabel = new Label("User");
        userLabel.getStyleClass().add("user-label");
        Label uidLabel = new Label("UID: 1234567890");
        uidLabel.getStyleClass().add("uid-label");

        VBox userInfo = new VBox();
        userInfo.getChildren().addAll(userLabel, uidLabel);
        userInfo.setSpacing(2);
        userInfo.setAlignment(Pos.CENTER_LEFT);

        HBox userBox = new HBox();
        userBox.getChildren().addAll(profile, userInfo);
        userBox.setSpacing(20);
        userBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(profile, new Insets(0, 0, 0, 20));

        return userBox;
    }

    private Parent leftBar() {
        VBox leftBar = new VBox();
        leftBar.getStyleClass().add("left-box");

        VBox userBox = new VBox();
        userBox.getStyleClass().add("user-box");
        userBox.getChildren().add(userInfo());
        userBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(userBox, Priority.NEVER);

        VBox friendList = new VBox();

        friendList.getChildren().addAll(chats);

        ScrollPane scrollPane = new ScrollPane(friendList);
        scrollPane.getStyleClass().add("scroll-pane");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        leftBar.getChildren().addAll(userBox, scrollPane);

        return leftBar;
    }

    private Parent chatBox() {
        VBox chatBox = new VBox();
        ImageView logo = TemplateView.createImageView("/images/chat-icon.png", 250, 250);
        chatBox.getChildren().add(logo);
        chatBox.setAlignment(Pos.CENTER);

        return chatBox;
    }

    public Button getAddFriendButton() {
        return addFriendButton;
    }

    public Button getFriendListButton() {
        return friendListButton;
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public Button getSettingButton() {
        return settingButton;
    }

    public ArrayList<Pane> getChats() {
        return chats;
    }
}
