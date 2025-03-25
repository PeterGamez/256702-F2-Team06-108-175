package com.mechat.screens;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddFriendScreen implements ScreenInterface {

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        //header
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER_LEFT);

        Button backButton = createImageButton("images/back-button.png", 30, 30, "back-button");
        backButton.setOnAction(e -> {
            ScreenHandler.setScreen(new MainChatScreen());
        });

        Label title = new Label("Add Friend");
        title.getStyleClass().add("misc-label");

        StackPane titlePane = new StackPane(title);
        titlePane.setAlignment(Pos.CENTER);
        HBox.setHgrow(titlePane, Priority.ALWAYS);

        header.getChildren().addAll(backButton, titlePane);
        header.setPadding(new Insets(0, 70, 0, 0));

        //content
        VBox box = new VBox();

        TextField friendNameField = new TextField();
        friendNameField.setPromptText("Enter friend's name");
        friendNameField.getStyleClass().add("friend-name-field");

        ImageView imageView = new ImageView("images/profile-icon.png");
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Button addFriendButton = new Button("Add Friend");
        addFriendButton.getStyleClass().add("add-friend-button");

        box.getChildren().addAll(friendNameField, imageView, addFriendButton);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(40, 60, 0, 60));
        box.setSpacing(30);

        root.setTop(header);
        root.setCenter(box);
        return root;
    }

    private Button createImageButton(String imagePath, double width, double height, String styleClass) {
        Button button = new Button();
        ImageView imageView = createImageView(imagePath, width, height);
        button.setGraphic(imageView);
        button.getStyleClass().add(styleClass);
        return button;
    }

    private ImageView createImageView(String imagePath, double width, double height) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(new javafx.scene.image.Image(imagePath));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }

    private void backEvent(MouseEvent e) {
        ScreenHandler.setScreen(new MainChatScreen());
    }
}