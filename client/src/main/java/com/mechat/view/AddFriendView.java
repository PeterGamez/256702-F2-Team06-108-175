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
import javafx.scene.layout.VBox;

public class AddFriendView implements ScreenInterface {

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        //header
        VBox header = new VBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER);

        Label title = new Label("Add Friend");
        title.getStyleClass().add("misc-label");

        TextField friendNameField = new TextField();
        friendNameField.setPromptText("Enter friend's name");
        friendNameField.getStyleClass().add("friend-name-field");

        header.getChildren().addAll(title, friendNameField);
        header.setSpacing(20);
        header.setPadding(new Insets(20, 80, 0, 80));

        //content
        root.setTop(header);
        root.setCenter(createFriendInfo()); //ถ้าไม่มีข้อมูลจะไม่ขึ้น
        root.setBottom(TemplateView.navBar());
        return root;
    }

    public Parent createFriendInfo() {
        VBox box = new VBox();

        ImageView imageView = new ImageView("/images/profile-icon.png");
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);

        Button addFriendButton = new Button("Add Friend");
        addFriendButton.getStyleClass().add("add-friend-button");

        box.getChildren().addAll(imageView, addFriendButton);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(100, 0, 0, 0));
        box.setSpacing(50);
        return box;
    }

}
