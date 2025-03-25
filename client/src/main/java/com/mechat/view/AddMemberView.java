package com.mechat.view;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddMemberView implements ScreenInterface {

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        //header
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER_LEFT);


        Label title = new Label("Add Member");
        title.getStyleClass().add("misc-label");

        StackPane titlePane = new StackPane(title);
        titlePane.setAlignment(Pos.CENTER);
        HBox.setHgrow(titlePane, Priority.ALWAYS);

        header.getChildren().add(titlePane);

        //content
        VBox box = new VBox();

        TextField friendNameField = new TextField();
        friendNameField.setPromptText("Enter friend's name");
        friendNameField.getStyleClass().add("friend-name-field");

        ImageView imageView = new ImageView("/images/profile-icon.png");
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Button addFriendButton = new Button("Add Friend");
        addFriendButton.getStyleClass().add("add-friend-button");

        box.getChildren().addAll(friendNameField, imageView, addFriendButton);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(40, 60, 0, 60));
        box.setSpacing(30);

        root.setTop(header);
        root.setCenter(box);
        root.setBottom(TemplateView.navBar());
        return root;
    }
}