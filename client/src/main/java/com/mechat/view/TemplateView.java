package com.mechat.view;

import com.mechat.ScreenHandler;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TemplateView {

    public static final ImageView createImageView(String imagePath, double width, double height) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    public static final Button createImageButton(String imagePath, double width, double height, String styleClass) {
        Button button = new Button();
        ImageView imageView = createImageView(imagePath, width, height);
        button.setGraphic(imageView);
        button.getStyleClass().add(styleClass);
        return button;
    }

    public static final Parent navBar() {
        Button addFriendButton = createImageButton("/images/add-friend-icon.png", 30, 30, "nav-button");
        addFriendButton.setOnAction(e -> ScreenHandler.setScreen(new AddFriendView()));
        Button friendList = createImageButton("/images/view-member-icon.png", 30, 30, "nav-button");
        friendList.setOnAction(e -> ScreenHandler.setScreen(new FriendListView()));
        Button homeButton = createImageButton("/images/home-icon.png", 30, 30, "nav-button");
        homeButton.setOnAction(e -> ScreenHandler.setScreen(new MainChatView()));
        Button settingButton = createImageButton("/images/settings-icon.png", 30, 30, "nav-button");
        settingButton.setOnAction(e -> ScreenHandler.setScreen(new SettingView()));

        HBox navBar = new HBox();
        navBar.getStyleClass().add("nav-bar");
        navBar.getChildren().addAll(addFriendButton, friendList, homeButton, settingButton);
        navBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(navBar, Priority.ALWAYS);

        return navBar;
    }

    public static VBox createButtonLayout(String imagePath, String labelText) {
        VBox buttonLayout = new VBox();
        Button button = TemplateView.createImageButton(imagePath, 40, 40, "nav-button");
        Label label = new Label(labelText);
        label.getStyleClass().add("friend-label");
        buttonLayout.getChildren().addAll(button, label);
        buttonLayout.setAlignment(Pos.CENTER);
        return buttonLayout;
    }

    public static Button createButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        return button;
    }
}
