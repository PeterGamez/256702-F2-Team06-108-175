package com.mechat.view.home;

import com.mechat.interfaces.ViewInterface;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView implements ViewInterface {

    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private StringProperty showErrorProperty;

    private Button loginButton;
    private Button backButton;

    public LoginView() {
        usernameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
        showErrorProperty = new SimpleStringProperty();

        loginButton = new Button();
        backButton = new Button();
    }

    @Override
    public Parent createContent() {
        // row 1
        VBox row1 = new VBox();

        Label content = new Label("Login");
        content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("title-label");

        row1.getChildren().addAll(content);
        row1.setAlignment(Pos.CENTER);

        // Error
        HBox errorBox = new HBox();

        Label error = new Label("");
        error.textProperty().bindBidirectional(showErrorProperty);
        error.getStyleClass().add("error-label");

        errorBox.getChildren().add(error);
        errorBox.setAlignment(Pos.CENTER);

        // row 2
        HBox row2 = new HBox();

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("login-field");
        usernameField.textProperty().bindBidirectional(usernameProperty);

        row2.getChildren().addAll(usernameField);
        row2.setAlignment(Pos.CENTER);

        // row 3
        HBox row3 = new HBox();

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("login-field");
        passwordField.textProperty().bindBidirectional(passwordProperty);

        row3.getChildren().add(passwordField);
        row3.setAlignment(Pos.CENTER);

        // row 4
        HBox row4 = new HBox();

        loginButton.setText("Sign In");
        loginButton.getStyleClass().add("button");

        backButton.setText("Back");
        backButton.getStyleClass().add("button");

        row4.getChildren().addAll(backButton, loginButton);
        row4.setSpacing(80);
        row4.setAlignment(Pos.CENTER);

        // horizontal layout
        VBox textFields = new VBox();

        textFields.getChildren().addAll(errorBox, row2, row3);
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

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public StringProperty getPasswordProperty() {
        return passwordProperty;
    }

    public StringProperty getShowErrorProperty() {
        return showErrorProperty;
    }
}
