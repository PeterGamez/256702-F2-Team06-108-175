package com.mechat.view;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ScreenInterface;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView implements ScreenInterface {

    private TextField usernameField;
    private PasswordField passwordField;

    @Override
    public Parent createContent() {
        // row 1
        HBox row1 = new HBox();

        Label content = new Label("Login");
        content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("title-label");

        row1.getChildren().addAll(content);
        row1.setAlignment(Pos.CENTER);

        // row 2
        HBox row2 = new HBox();

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("login-field");

        row2.getChildren().add(usernameField);
        row2.setAlignment(Pos.CENTER);

        // row 3
        HBox row3 = new HBox();

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("login-field");

        row3.getChildren().add(passwordField);
        row3.setAlignment(Pos.CENTER);

        // row 4
        HBox row4 = new HBox();

        Button loginButton = new Button("Sign In");
        loginButton.getStyleClass().add("button");
        loginButton.setOnAction(e -> loginEvent(e));

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> backToOriginalMainView(e));

        row4.getChildren().addAll(backButton, loginButton);
        row4.setSpacing(80);
        row4.setAlignment(Pos.CENTER);

        // horizontal layout
        VBox textFields = new VBox();

        textFields.getChildren().addAll(row2, row3);
        textFields.setSpacing(10);

        VBox titleTextField = new VBox();

        titleTextField.getChildren().addAll(row1, textFields);
        titleTextField.setSpacing(20);

        VBox box = new VBox();

        box.getChildren().addAll(titleTextField, row4);
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);

        return box;
    }

    private void loginEvent(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();
    }

    private void backToOriginalMainView(ActionEvent e) {
        ScreenHandler.setScreen(new RegorLogView());
    }
}
