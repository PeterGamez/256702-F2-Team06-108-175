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

public class RegisterView implements ScreenInterface {

    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;

    @Override
    public Parent createContent() {
        // row 1
        HBox row1 = new HBox();

        Label content = new Label("Register");
        content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("title-label");

        row1.getChildren().addAll(content);
        row1.setAlignment(Pos.CENTER);

        // row 2
        HBox row2 = new HBox();

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("reg-field");

        row2.getChildren().add(usernameField);
        row2.setAlignment(Pos.CENTER);

        // row 3
        HBox row3 = new HBox();

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("reg-field");

        row3.getChildren().add(passwordField);
        row3.setAlignment(Pos.CENTER);

        HBox row4 = new HBox();

        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Password");
        confirmPasswordField.getStyleClass().add("reg-field");

        row4.getChildren().add(confirmPasswordField);
        row4.setAlignment(Pos.CENTER);

        // row 5
        HBox row5 = new HBox();

        Button loginButton = new Button("Sign Up");
        loginButton.getStyleClass().add("button");
        loginButton.setOnAction(e -> registerEvent(e));

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> backEvent(e));

        row5.getChildren().addAll(backButton, loginButton);
        row5.setSpacing(80);
        row5.setAlignment(Pos.CENTER);

       

        // horizontal layout
        VBox textFields = new VBox();
        
        textFields.getChildren().addAll(row2, row3, row4);
        textFields.setSpacing(10);

        VBox titleTextField = new VBox();

        titleTextField.getChildren().addAll(row1, textFields);
        titleTextField.setSpacing(20);

        VBox box = new VBox();

        box.getChildren().addAll(titleTextField, row5);
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);

        return box;
    }

    private void registerEvent(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
    }

    private void backEvent(ActionEvent e) {
        ScreenHandler.setScreen(new MainView());
    }
}
