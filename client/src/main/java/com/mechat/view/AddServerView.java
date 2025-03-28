package com.mechat.view;

import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.TemplateView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddServerView implements ViewInterface {

    private StringProperty serverNameProperty;
    private StringProperty serverAddressProperty;

    private Button doneButton;
    private Button cancelButton;

    public AddServerView() {
        serverNameProperty = new SimpleStringProperty();
        serverAddressProperty = new SimpleStringProperty();

        doneButton = TemplateView.createButton("Done", "button");
        cancelButton = TemplateView.createButton("Cancel", "button");
    }

    @Override
    public Parent createContent() {
        // header
        Label title = new Label("Server Info");
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("title-label");

        // text fields
        VBox serverNameBox = createLabeledTextField("Server Name", "Enter Server Name", serverNameProperty);
        VBox serverAdressBox = createLabeledTextField("Server Adress", "Enter Server Adress", serverAddressProperty);

        // buttons
        HBox buttonLayout = new HBox();

        buttonLayout.getChildren().addAll(cancelButton, doneButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(60);
        buttonLayout.setPadding(new Insets(40, 0, 0, 0));

        // main layout
        VBox box = new VBox();
        box.getChildren().addAll(title, serverNameBox, serverAdressBox, buttonLayout);
        box.setAlignment(Pos.TOP_CENTER);
        box.setSpacing(20);
        box.setPadding(new Insets(40, 0, 0, 0));
        return box;
    }

    private VBox createLabeledTextField(String labelText, String promptText, StringProperty property) {
        VBox box = new VBox();
        Label label = new Label(labelText);
        label.getStyleClass().add("server-label");

        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.getStyleClass().add("server-field");
        textField.textProperty().bindBidirectional(property);

        box.getChildren().addAll(label, textField);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setSpacing(10);
        box.setPadding(new Insets(0, 100, 0, 100));

        return box;
    }

    public StringProperty getServerNameProperty() {
        return serverNameProperty;
    }

    public StringProperty getServerAddressProperty() {
        return serverAddressProperty;
    }

    public Button getDoneButton() {
        return doneButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
