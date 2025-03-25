package com.mechat.view;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ScreenInterface;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView implements ScreenInterface {

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
        row1.setAlignment(Pos.CENTER);

        // row 2
        HBox row2 = new HBox();

        Label title = new Label("A Real-Time Chat Application in Java");
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("misc-label");

        row2.getChildren().add(title);
        row2.setAlignment(Pos.CENTER);

        // row 3
        HBox row3 = new HBox();

        Button leftButton = new Button("Register");
        leftButton.getStyleClass().add("button");
        leftButton.setOnAction(e -> registerEvent(e));

        Button rightButton = new Button("Login");
        rightButton.getStyleClass().add("button");
        rightButton.setOnAction(e -> loginEvent(e));

        row3.getChildren().addAll(leftButton, rightButton);
        row3.setSpacing(80);
        row3.setAlignment(Pos.CENTER);

        // horizontal layout
        VBox box = new VBox();

        box.getChildren().addAll(row1, row2, row3);
        box.setSpacing(93);
        box.setAlignment(Pos.CENTER);

        return box;
    }

    private void registerEvent(ActionEvent e) {
        ScreenHandler.setScreen(new RegisterView());
    }

    private void loginEvent(ActionEvent e) {
        ScreenHandler.setScreen(new LoginView());
    }
}
