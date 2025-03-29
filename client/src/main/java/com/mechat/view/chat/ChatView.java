package com.mechat.view.chat;

import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.TemplateView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.ContextMenu;

public class ChatView implements ViewInterface {

    private StringProperty friendNameProperty;
    private StringProperty friendImageProperty;

    // private ContextMenu senderRightClickMenu;

    // private MenuItem senderCopyMenuItem;
    // private MenuItem editMenuItem;
    // private MenuItem deleteMenuItem;

    // private ContextMenu receiverRightClickMenu;

    // private MenuItem receiverCopyMenuItem;

    // private Button informationButton;
    private Button backButton;

    private TextField messageField;

    private VBox chatBox;

    // private ScrollPane chatScrollPane;

    public ChatView() {
        friendNameProperty = new SimpleStringProperty();
        friendImageProperty = new SimpleStringProperty();

        // senderCopyMenuItem = new MenuItem("Copy");
        // editMenuItem = new MenuItem("Edit");
        // deleteMenuItem = new MenuItem("Delete");

        // senderRightClickMenu = new ContextMenu();
        // senderRightClickMenu.getItems().addAll(senderCopyMenuItem, editMenuItem, deleteMenuItem);

        // receiverCopyMenuItem = new MenuItem("Copy");

        // receiverRightClickMenu = new ContextMenu();
        // receiverRightClickMenu.getItems().add(receiverCopyMenuItem);

        // informationButton = TemplateView.createImageButton("/images/info-button.png",
        // 30, 30, "back-button");
        backButton = TemplateView.createImageButton("/images/back-button.png", 30, 30, "back-button");

        messageField = new TextField();

        chatBox = new VBox();

        // chatScrollPane = new ScrollPane(chatBox);
    }

    @Override
    public Parent createContent() {

        VBox root = new VBox();
        root.getStyleClass().add("root");
        root.getChildren().addAll(createHeader(), createChatBox(), createMessageField());

        return root;
    }

    private Parent createHeader() {
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER_LEFT);

        ImageView profile = TemplateView.createImageView("/images/profile-icon.png", 60, 60);
        TemplateView.bindImage(profile, friendImageProperty);

        Label friendName = new Label();
        friendName.getStyleClass().add("friend-name-label");
        friendName.textProperty().bind(friendNameProperty);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // header.getChildren().addAll(backButton, profile, friendName, spacer,
        // informationButton);
        header.getChildren().addAll(backButton, profile, friendName, spacer);
        header.setSpacing(20);
        header.setPadding(new Insets(10, 20, 20, 20));

        return header;
    }

    private Parent createChatBox() {
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

        messageField.setPromptText("Message @Friend 1");
        messageField.getStyleClass().add("message-field");
        HBox.setHgrow(messageField, Priority.ALWAYS);

        messageBox.getChildren().addAll(messageField);
        messageBox.setSpacing(20);
        messageBox.setPadding(new Insets(10, 20, 20, 20));

        return messageBox;
    }

    public void addReceivedMessage(String message, String time) {
        HBox messageBox = new HBox();
        messageBox.setAlignment(Pos.CENTER_LEFT);
        messageBox.setPadding(new Insets(5, 10, 5, 10));

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("received-message");

        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("time-label");

        messageBox.getChildren().addAll(messageLabel, timeLabel);
        messageBox.setSpacing(10);

        // messageBox.setOnContextMenuRequested(e -> {
        //     receiverRightClickMenu.show(messageBox, e.getScreenX(), e.getScreenY());
        // });

        chatBox.getChildren().add(messageBox);
    }

    public void addSentMessage(String message, String time) {
        HBox messageBox = new HBox();
        messageBox.setAlignment(Pos.CENTER_RIGHT);
        messageBox.setPadding(new Insets(5, 10, 5, 10));

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("sent-message");

        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("time-label");

        messageBox.getChildren().addAll(timeLabel, messageLabel);
        messageBox.setSpacing(10);

        // messageBox.setOnContextMenuRequested(e -> {
        //     senderRightClickMenu.show(messageBox, e.getScreenX(), e.getScreenY());
        // });

        chatBox.getChildren().add(messageBox);
    }

    // public Button getInformationButton() {
    // return informationButton;
    // }

    public StringProperty getFriendNameProperty() {
        return friendNameProperty;
    }

    public StringProperty getFriendImageProperty() {
        return friendImageProperty;
    }

    public Button getBackButton() {
        return backButton;
    }

    public TextField getMessageField() {
        return messageField;
    }

    public VBox getChatBox() {
        return chatBox;
    }

    // public ContextMenu getSenderRightClickMenu() {
    //     return senderRightClickMenu;
    // }

    // public ContextMenu getReceiverRightClickMenu() {
    //     return receiverRightClickMenu;
    // }

    // public MenuItem getSenderCopyMenuItem() {
    //     return senderCopyMenuItem;
    // }

    // public MenuItem getReceiverCopyMenuItem() {
    //     return receiverCopyMenuItem;
    // }

    // public MenuItem getEditMenuItem() {
    //     return editMenuItem;
    // }

    // public MenuItem getDeleteMenuItem() {
    //     return deleteMenuItem;
    // }
}
