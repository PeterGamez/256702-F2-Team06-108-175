package com.mechat.screens;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ChatScreen implements ScreenInterface {

    private VBox chatBox;
    private TextField messageField;

    @Override
    public Parent createContent() {

        VBox root = new VBox();
        root.getStyleClass().add("root");
        root.getChildren().addAll(createHeader(), createChatBox(), createMessageField());

        return root;
    }

    public Parent createHeader() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER_LEFT);

        ImageView backButton = createImageView("images/back-button.png", 30, 30);
        backButton.setOnMouseClicked(e -> backEvent(e));

        ImageView profile = createImageView("images/profile-icon.png", 60, 60);

        Label friendName = new Label("Friend's Name");
        friendName.getStyleClass().add("friend-name-label");

        ImageView information = createImageView("images/info-button.png", 40, 40);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(backButton, profile, friendName, spacer, information);
        header.setSpacing(20);
        header.setPadding(new Insets(10, 20, 10, 20));

        return header;
    }

    private Parent createChatBox() {
        chatBox = new VBox();
        chatBox.getStyleClass().add("chat-box");

        ScrollPane chatScrollPane = new ScrollPane(chatBox);
        chatScrollPane.getStyleClass().add("chat-scroll-pane");
        chatScrollPane.setFitToWidth(true);
        VBox.setVgrow(chatScrollPane, Priority.ALWAYS);

        addReceivedMessage("Who are you?", getCurrentTime());
        addReceivedMessage("My name is Friend 1", getCurrentTime());

        return chatScrollPane;
    }

    private Parent createMessageField() {
        HBox messageBox = new HBox();
        messageBox.getStyleClass().add("message-box");
        messageBox.setAlignment(Pos.CENTER);

        messageField = new TextField();
        messageField.setPromptText("Message @Friend 1");
        messageField.getStyleClass().add("message-field");
        HBox.setHgrow(messageField, Priority.ALWAYS);

        ImageView attachButton = createImageView("images/attach-button.png", 30, 30);

        messageField.setOnAction(e -> {
            String message = messageField.getText();
            if (!message.trim().isEmpty()) {
                addSentMessage(message, getCurrentTime());
                messageField.clear();
            }
        });

        messageBox.getChildren().addAll(messageField, attachButton);
        messageBox.setSpacing(20);
        messageBox.setPadding(new Insets(10, 20, 10, 20));

        return messageBox;
    }

    private void backEvent(MouseEvent e) {
        ScreenHandler.setScreen(new MainChatScreen());
    }

    private ImageView createImageView(String imagePath, double width, double height) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
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
