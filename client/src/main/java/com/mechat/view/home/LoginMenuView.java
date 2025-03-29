package com.mechat.view.home;

import com.mechat.interfaces.ViewInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginMenuView implements ViewInterface {

    private Button backButton;
    private Button registerButton;
    private Button loginButton;

    public LoginMenuView() {
        backButton = createButton("Back to Server");
        registerButton = createButton("Register");
        loginButton = createButton("Login");
    }

    @Override
    public Parent createContent() {
        // row 1
        HBox row1 = new HBox();

        Image image = new Image("/images/main-icon.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Label content = new Label("MECHAT");
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-text-fill: #44ad53;");

        row1.getChildren().addAll(imageView, content);
        row1.setSpacing(20);
        row1.setAlignment(Pos.TOP_CENTER);

        // row 2
        HBox row2 = new HBox();

        Label title = new Label("A Real-Time Chat Application in Java");
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("misc-label");

        row2.getChildren().add(title);
        row2.setAlignment(Pos.CENTER);
        row2.setPadding(new Insets(80, 0, 0, 0));

        // row 3
        HBox row3 = new HBox();

        row3.getChildren().addAll(backButton, registerButton, loginButton);
        row3.setSpacing(30);
        row3.setAlignment(Pos.CENTER);
        row3.setPadding(new Insets(100, 0, 0, 0));

        // horizontal layout
        VBox box = new VBox();

        box.getChildren().addAll(row1, row2, row3);
        box.setSpacing(20);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(60, 0, 0, 0));

        return box;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("button");
        return button;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public Button getLoginButton() {
        return loginButton;
    }
}
