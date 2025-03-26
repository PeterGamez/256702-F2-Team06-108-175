package com.mechat.view;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ScreenInterface;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegorLogView implements ScreenInterface {

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

        Button backButton = new Button("Back to Server");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> serverEvent(e));

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("button");
        registerButton.setOnAction(e -> registerEvent(e));

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button");
        loginButton.setOnAction(e -> loginEvent(e));


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

    private void registerEvent(ActionEvent e) {
        ScreenHandler.setScreen(new RegisterView());
    }

    private void loginEvent(ActionEvent e) {
        ScreenHandler.setScreen(new LoginView());
    }

    private void serverEvent(ActionEvent e) {
        ScreenHandler.setScreen(new ServerView());
    }
}
