package com.mechat.view.navbar;

import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.NavbarView;
import com.mechat.utils.TemplateView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddFriendView extends NavbarView implements ViewInterface {

    private StringProperty searchedFriendName;
    private StringProperty imagePath;
    private StringProperty showFriendName;
    private StringProperty showErrorProperty;

    private Button addFriendButton;

    public AddFriendView() {
        searchedFriendName = new SimpleStringProperty();
        imagePath = new SimpleStringProperty();
        showFriendName = new SimpleStringProperty();
        showErrorProperty = new SimpleStringProperty();

        addFriendButton = new Button();
        addFriendButton.setVisible(false);
    }

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        // Header
        VBox header = new VBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER);

        Label title = new Label("Add Friend");
        title.getStyleClass().add("misc-label");

        // Error message
        HBox errorBox = new HBox();

        Label error = new Label("");
        error.textProperty().bindBidirectional(showErrorProperty);
        error.getStyleClass().add("error-label");

        errorBox.getChildren().add(error);
        errorBox.setAlignment(Pos.CENTER);

        // Search friend field
        TextField friendNameField = new TextField();
        friendNameField.setPromptText("Enter friend's name");
        friendNameField.getStyleClass().add("friend-name-field");
        friendNameField.textProperty().bindBidirectional(searchedFriendName);

        header.getChildren().addAll(title, errorBox, friendNameField);
        header.setSpacing(20);
        header.setPadding(new Insets(20, 80, 0, 80));

        // Content
        root.setTop(header);
        root.setCenter(createFriendInfo());
        root.setBottom(super.setNavbar());
        return root;
    }

    public Parent createFriendInfo() {
        VBox box = new VBox();

        ImageView imageView = new ImageView();
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        imageView.setClip(TemplateView.createCircle(imageView));

        TemplateView.bindImage(imageView, imagePath);
        Label friendName = new Label();
        friendName.textProperty().bind(showFriendName);
        friendName.getStyleClass().add("friend-name-label");

        addFriendButton.setText("Add Friend");
        addFriendButton.getStyleClass().add("add-friend-button");

        box.getChildren().addAll(imageView, friendName, addFriendButton);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(100, 0, 0, 0));
        box.setSpacing(50);
        return box;
    }

    public StringProperty getSearchedFriendName() {
        return searchedFriendName;
    }

    public StringProperty getImagePath() {
        return imagePath;
    }

    public StringProperty getShowFriendName() {
        return showFriendName;
    }

    public Button getAddFriendButton() {
        return addFriendButton;
    }

    public StringProperty getShowErrorProperty() {
        return showErrorProperty;
    }
}
