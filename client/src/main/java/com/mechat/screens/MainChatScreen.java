package com.mechat.screens;

import java.util.ArrayList;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainChatScreen implements ScreenInterface {

    private ArrayList<Pane> chats = new ArrayList<>();

    @Override
    public Parent createContent() {

        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar");

        Label userLabel = new Label("USER\nUUID: 000000001");
        userLabel.getStyleClass().add("user-label");

        VBox userBar = new VBox();
        userBar.getStyleClass().add("user-bar");
        userBar.getChildren().add(userLabel);
        userBar.setAlignment(Pos.CENTER);

        VBox chatBar = new VBox();

        for (int i = 0; i < 20; i++) {
            addChat("", "Chat " + (i + 1));
        }

        chatBar.getChildren().addAll(chats);

        sidebar.getChildren().add(userBar);
        sidebar.getChildren().add(chatBar);

        ScrollPane scrollPane = new ScrollPane(sidebar);
        scrollPane.setFitToWidth(true);

        
        Pane chatArea = new Pane();
        chatArea.getStyleClass().add("chat-area");

        HBox navBar = new HBox();
        navBar.getStyleClass().add("nav-bar");

        BorderPane root = new BorderPane();
        root.setLeft(scrollPane);
        root.setCenter(chatArea);
        root.setBottom(navBar);

        return root;
    }

    public void addChat(String avatar, String title) {
        VBox chat = new VBox();

        Label label = new Label(title);
        label.getStyleClass().add("friend-label");

        chat.getChildren().add(label);
        chat.getStyleClass().add("chats-bar");
        chat.setAlignment(Pos.CENTER);

        chats.add(chat);

    }
}
