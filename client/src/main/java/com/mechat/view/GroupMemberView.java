package com.mechat.view;

import java.util.ArrayList;

import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GroupMemberView implements ViewInterface {

    private ArrayList<Pane> friends = new ArrayList<>();
    private Button backButton;

    public GroupMemberView() {
        backButton = TemplateView.createImageButton("/images/back-button.png", 30, 30, "back-button");
    }
    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        //header
        Label title = new Label("Group Member");
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("misc-label");

        HBox header = new HBox(backButton, title);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(20);
        header.getStyleClass().add("header");

        //content
        VBox friendList = new VBox();

        for (int i = 0; i < 20; i++) {
            addChat("", "Friend " + (i + 1));
        }

        friendList.getChildren().addAll(friends);

        ScrollPane scrollPane = new ScrollPane(friendList);
        scrollPane.getStyleClass().add("scroll-pane");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox box = new VBox(scrollPane);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(20,100,70,100));
        box.setSpacing(30);

        root.setTop(header);
        root.setCenter(box);
        return root;
    }

    public void addChat(String avatar, String title) {
        VBox list = new VBox();
        list.setAlignment(Pos.CENTER);

        Label label = new Label(title);
        label.getStyleClass().add("friend-label");

        ImageView profile = TemplateView.createImageView("/images/profile-icon.png", 50, 50);

        HBox chatHeader = new HBox();
        chatHeader.getChildren().addAll(profile, label);
        chatHeader.setSpacing(20);
        chatHeader.setAlignment(Pos.CENTER_LEFT);

        list.getChildren().add(chatHeader);
        list.getStyleClass().add("friend-box");

        friends.add(list);
    }

    public Button getBackButton() {
        return backButton;
    }
}