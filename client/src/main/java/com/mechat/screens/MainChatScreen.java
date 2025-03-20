package com.mechat.screens;

import java.util.ArrayList;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainChatScreen implements ScreenInterface {

    private ArrayList<Pane> chats = new ArrayList<>();

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();
        root.setLeft(leftBar());
        root.setBottom(navBar());
        root.setCenter(chatBox());
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

    public Parent leftBar() {
        VBox leftBar = new VBox();
        leftBar.getStyleClass().add("left-box");

        VBox userBox = new VBox();
        userBox.getStyleClass().add("user-box");
        userBox.getChildren().add(userInfo());
        userBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(userBox, Priority.NEVER);

        VBox friendList = new VBox();

        for (int i = 0; i < 99; i++) {
            addChat("", "Friend " + (i + 1));
        }

        friendList.getChildren().addAll(chats);

        ScrollPane scrollPane = new ScrollPane(friendList);
        scrollPane.getStyleClass().add("scroll-pane");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        leftBar.getChildren().addAll(userBox, scrollPane);

        return leftBar;
    }

    public Parent navBar() {
        ImageView addfriendView = new ImageView(new Image("/images/add-friend-icon.png"));
        addfriendView.setFitWidth(30);
        addfriendView.setFitHeight(30);

        ImageView homeView = new ImageView(new Image("/images/home-icon.png"));
        homeView.setFitWidth(30);
        homeView.setFitHeight(30);

        ImageView settingView = new ImageView(new Image("/images/settings-icon.png"));
        settingView.setFitWidth(30);
        settingView.setFitHeight(30);

        HBox navBar = new HBox();
        navBar.getStyleClass().add("nav-bar");
        navBar.getChildren().addAll(addfriendView, homeView, settingView);
        navBar.setSpacing(180);
        navBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(navBar, Priority.ALWAYS);

        return navBar;
    }

    public Parent chatBox() {
        VBox chatBox = new VBox();
        ImageView logo = new ImageView("images/chat-icon.png");
        logo.setFitHeight(300);
        logo.setFitWidth(300);
        chatBox.getChildren().add(logo);
        chatBox.setAlignment(Pos.CENTER);

        return chatBox;
    }
}
