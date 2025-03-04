package com.mechat.screens;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ChatScreen implements ScreenInterface {

    private VBox chatBox;

    @Override
    public Parent createContent() {
        // Header
        HBox header = new HBox();
        header.getStyleClass().add("header");

        ImageView backButton = new ImageView(new Image("images/back-button.png"));
        backButton.setFitWidth(20);
        backButton.setFitHeight(20);

        ImageView profileIcon = new ImageView(new Image("images/profile-icon.png"));
        profileIcon.setFitWidth(40);
        profileIcon.setFitHeight(40);

        Label friendName = new Label("Friend's Name");
        friendName.getStyleClass().add("friend-name");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView infoButton = new ImageView("images/info-button.png");
        infoButton.setFitWidth(25);
        infoButton.setFitHeight(25);

        header.getChildren().addAll(backButton, profileIcon, friendName, spacer, infoButton);
        header.setSpacing(20);

        // Chat messages area
        chatBox = new VBox();
        chatBox.getStyleClass().add("chat-box");

        // Example messages
        addReceivedMessage("Who are you?", "12:02");
        addReceivedMessage("My name is Friend 1", "12:02");
        addSentMessage("Hello, Friend 1", "12:03");

        ScrollPane chatScrollPane = new ScrollPane(chatBox);
        chatScrollPane.getStyleClass().add("chat-scroll-pane");
        chatScrollPane.setFitToWidth(true);
        VBox.setVgrow(chatScrollPane, Priority.ALWAYS);

        // Message input area
        HBox inputArea = new HBox();
        inputArea.getStyleClass().add("input-area");

        TextField messageField = new TextField();
        messageField.setPromptText("Message @Friend 1");
        messageField.setPrefWidth(500);
        messageField.getStyleClass().add("message-field");
        HBox.setHgrow(messageField, Priority.ALWAYS);

        ImageView attachButton = new ImageView(new Image("images/attach-button.png"));
        attachButton.setFitWidth(30);
        attachButton.setFitHeight(30);
        attachButton.setTranslateY(10);

        inputArea.getChildren().addAll(attachButton, messageField);
        inputArea.setSpacing(10);

        // Main layout
        VBox root = new VBox();
        root.getChildren().addAll(header, chatScrollPane, inputArea);
        VBox.setVgrow(root, Priority.ALWAYS);

        return root;
    }

    private void addReceivedMessage(String message, String time) {
        HBox messageBox = new HBox();
        messageBox.setAlignment(Pos.CENTER_LEFT);
        messageBox.setPadding(new Insets(5, 10, 5, 10));

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("received-message");

        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("time-label");

        messageBox.getChildren().addAll(messageLabel, timeLabel);
        messageBox.setSpacing(10);
        chatBox.getChildren().add(messageBox);
    }

    private void addSentMessage(String message, String time) {
        HBox messageBox = new HBox();
        messageBox.setAlignment(Pos.CENTER_RIGHT);
        messageBox.setPadding(new Insets(5, 10, 5, 10));

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("sent-message");

        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("time-label");

        messageBox.getChildren().addAll(timeLabel, messageLabel);
        messageBox.setSpacing(10);
        chatBox.getChildren().add(messageBox);
    }

    private String getCurrentTime() {
        return java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
    }
}
