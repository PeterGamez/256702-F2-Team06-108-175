package com.mechat.utils;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

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

    public static final HBox navBar() {
        Button addFriendButton = createImageButton("/images/add-friend-icon.png", 30, 30, "nav-button");
        Button friendListButton = createImageButton("/images/view-member-icon.png", 30, 30, "nav-button");
        Button homeButton = createImageButton("/images/home-icon.png", 30, 30, "nav-button");
        Button settingButton = createImageButton("/images/settings-icon.png", 30, 30, "nav-button");

        HBox navBar = new HBox();
        navBar.getStyleClass().add("nav-bar");
        navBar.getChildren().addAll(addFriendButton, friendListButton, homeButton, settingButton);
        navBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(navBar, Priority.ALWAYS);

        return navBar;
    }

    public static final VBox createButtonLayout(String imagePath, String labelText) {
        VBox buttonLayout = new VBox();
        Button button = TemplateView.createImageButton(imagePath, 40, 40, "nav-button");
        Label label = new Label(labelText);
        label.getStyleClass().add("friend-label");
        buttonLayout.getChildren().addAll(button, label);
        buttonLayout.setAlignment(Pos.CENTER);
        return buttonLayout;
    }

    public static final Button createButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        return button;
    }

    public static final Circle createCircle(ImageView imageView) {
        return new Circle(imageView.getFitWidth(), imageView.getFitHeight(), 60);
    }

    public static final void bindImage(ImageView imageView, StringProperty imagePath) {
        imagePath.addListener((obs, oldImagePath, newImagePath) -> {
            if (newImagePath != null && !newImagePath.isEmpty()) {
                imageView.setImage(new Image(newImagePath));
            }
        });
    }
}
