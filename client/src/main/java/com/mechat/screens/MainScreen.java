package com.mechat.screens;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainScreen implements ScreenInterface {

    @Override
    public Parent createContent() {
        // row 1
        HBox row1 = new HBox();

        Image image = new Image("/images/icon.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Label content = new Label("MECHAT");
        content.setAlignment(Pos.CENTER);

        row1.getChildren().addAll(imageView, content);
        row1.setSpacing(10);
        row1.setAlignment(Pos.CENTER);

        // row 2
        HBox row2 = new HBox();

        Label title = new Label("A Real-Time Chat Application in Java");
        title.setAlignment(Pos.CENTER);

        row2.getChildren().add(title);
        row2.setAlignment(Pos.CENTER);

        // row 3
        HBox row3 = new HBox();

        Button leftButton = new Button("Register");
        Button rightButton = new Button("Login");
        leftButton.getStyleClass().add("button");
        rightButton.getStyleClass().add("button");

        row3.getChildren().addAll(leftButton, rightButton);
        row3.setSpacing(20);
        row3.setAlignment(Pos.CENTER);

        // horizontal layout
        VBox box = new VBox();

        box.getChildren().addAll(row1, row2, row3);
        box.setSpacing(80);
        box.setAlignment(Pos.CENTER);

        return box;
    }
}
