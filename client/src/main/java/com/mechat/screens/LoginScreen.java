package com.mechat.screens;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginScreen implements ScreenInterface {

    @Override
    public Parent createContent() {
        // row 1
        HBox row1 = new HBox();

        Label content = new Label("Login");
        content.setAlignment(Pos.CENTER);

        row1.getChildren().addAll(content);
        row1.setAlignment(Pos.CENTER);

        // row 2
        HBox row2 = new HBox();

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        row2.getChildren().add(usernameField);
        row2.setAlignment(Pos.CENTER);

        // row 3
        HBox row3 = new HBox();

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        row3.getChildren().add(passwordField);
        row3.setAlignment(Pos.CENTER);

        // row 4
        HBox row4 = new HBox();

        Button loginButton = new Button("Register");
        loginButton.getStyleClass().add("button");

        row4.getChildren().addAll(loginButton);
        row4.setAlignment(Pos.CENTER);

        // horizontal layout
        VBox box = new VBox();

        box.getChildren().addAll(row1, row2, row3, row4);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);

        return box;
    }
}
