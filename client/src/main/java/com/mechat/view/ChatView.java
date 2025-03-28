package com.mechat.view;

import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ChatView implements ViewInterface {

    private VBox chatBox;
    private Button informationButton;
    private Button backButton;

    public ChatView() {
        informationButton = TemplateView.createImageButton("/images/info-button.png", 30, 30, "back-button");
        backButton = TemplateView.createImageButton("/images/back-button.png", 30, 30, "back-button");
    }

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

        ImageView profile = TemplateView.createImageView("/images/profile-icon.png", 60, 60);

        Label friendName = new Label("Friend's Name");
        friendName.getStyleClass().add("friend-name-label");

        // if(ถ้าเป็นกลุ่ม){
        // information.setOnAction(e -> ScreenHandler.setScreen(new GroupInfoView()));
        // }
        // else{
        // information.setOnAction(e -> ScreenHandler.setScreen(new FriendInfoView()));
        // }

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(backButton, profile, friendName, spacer, informationButton);
        header.setSpacing(20);
        header.setPadding(new Insets(10, 20, 20, 20));

        return header;
    }

    private Parent createChatBox() {
        chatBox = new VBox();
        chatBox.getStyleClass().add("chat-box");

        ScrollPane chatScrollPane = new ScrollPane(chatBox);
        chatScrollPane.getStyleClass().add("chat-scroll-pane");
        chatScrollPane.setFitToWidth(true);
        VBox.setVgrow(chatScrollPane, Priority.ALWAYS);

        return chatScrollPane;
    }

    private Parent createMessageField() {
        HBox messageBox = new HBox();
        messageBox.getStyleClass().add("message-box");
        messageBox.setAlignment(Pos.CENTER);

        TextField messageField = new TextField();
        messageField.setPromptText("Message @Friend 1");
        messageField.getStyleClass().add("message-field");
        HBox.setHgrow(messageField, Priority.ALWAYS);

        messageField.setOnAction(e -> {
            String message = messageField.getText();
            if (!message.trim().isEmpty()) {
                addSentMessage(message, getCurrentTime());
                messageField.clear();
            }
        });

        messageBox.getChildren().addAll(messageField);
        messageBox.setSpacing(20);
        messageBox.setPadding(new Insets(10, 20, 20, 20));

        return messageBox;
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

    public Button getInformationButton() {
        return informationButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
