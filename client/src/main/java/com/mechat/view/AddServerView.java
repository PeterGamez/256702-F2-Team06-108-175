package com.mechat.view;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddServerView implements ViewInterface {

    private Button doneButton;
    private Button cancelButton;

    @Override
    public Parent createContent() {
        //header
        Label title = new Label("Server Info");
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("title-label");

        //text fields
        VBox serverNameBox = createLabeledTextField("Server Name", "Enter Server Name");
        VBox serverAdressBox = createLabeledTextField("Server Adress", "Enter Server Adress");

        //buttons
        HBox buttonLayout = new HBox();

        doneButton = TemplateView.createButton("Done", "button");
        cancelButton = TemplateView.createButton("Cancel", "button");
        cancelButton.setOnAction(e -> {
            ScreenHandler.setScreen(new ServerView());
        });

        buttonLayout.getChildren().addAll(cancelButton, doneButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(60);
        buttonLayout.setPadding(new Insets(40, 0, 0, 0));

        //main layout
        VBox box = new VBox();
        box.getChildren().addAll(title, serverNameBox, serverAdressBox, buttonLayout);
        box.setAlignment(Pos.TOP_CENTER);
        box.setSpacing(20);
        box.setPadding(new Insets(40, 0, 0, 0));
        return box;
    }

    private VBox createLabeledTextField(String labelText, String promptText) {
        VBox box = new VBox();
        Label label = new Label(labelText);
        label.getStyleClass().add("server-label");
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.getStyleClass().add("server-field");
        box.getChildren().addAll(label, textField);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setSpacing(10);
        box.setPadding(new Insets(0, 100, 0, 100));
        return box;
    }

    public Button getDoneButton() {
        return doneButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
