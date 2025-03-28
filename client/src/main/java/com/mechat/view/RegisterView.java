package com.mechat.view;

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

public class RegisterView implements ViewInterface {

    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private StringProperty confirmPasswordProperty;
    private StringProperty showErrorProperty;

    Button registerButton;
    Button backButton;

    public RegisterView() {
        usernameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
        confirmPasswordProperty = new SimpleStringProperty();
        showErrorProperty = new SimpleStringProperty();

        registerButton = new Button();
        backButton = new Button();
    }

    @Override
    public Parent createContent() {
        // row 1
        HBox row1 = new HBox();

        Label content = new Label("Register");
        content.setAlignment(Pos.CENTER);
        content.getStyleClass().add("title-label");

        row1.getChildren().addAll(content);
        row1.setAlignment(Pos.CENTER);

        //Error
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
        usernameField.getStyleClass().add("reg-field");
        usernameField.textProperty().bindBidirectional(usernameProperty);

        row2.getChildren().add(usernameField);
        row2.setAlignment(Pos.CENTER);

        // row 3
        HBox row3 = new HBox();

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("reg-field");
        passwordField.textProperty().bindBidirectional(passwordProperty);

        row3.getChildren().add(passwordField);
        row3.setAlignment(Pos.CENTER);

        HBox row4 = new HBox();

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Password");
        confirmPasswordField.getStyleClass().add("reg-field");
        confirmPasswordField.textProperty().bindBidirectional(confirmPasswordProperty);

        row4.getChildren().add(confirmPasswordField);
        row4.setAlignment(Pos.CENTER);

        // row 5
        HBox row5 = new HBox();

        registerButton.setText("Sign Up");
        registerButton.getStyleClass().add("button");

        backButton.setText("Back");
        backButton.getStyleClass().add("button");

        row5.getChildren().addAll(backButton, registerButton);
        row5.setSpacing(80);
        row5.setAlignment(Pos.CENTER);

        // horizontal layout
        VBox textFields = new VBox();

        textFields.getChildren().addAll(errorBox, row2, row3, row4);
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

    public Button getBackButton() {
        return backButton;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public StringProperty getPasswordProperty() {
        return passwordProperty;
    }

    public StringProperty getConfirmPasswordProperty() {
        return confirmPasswordProperty;
    }

    public StringProperty getShowErrorProperty() {
        return showErrorProperty;
    }
}
