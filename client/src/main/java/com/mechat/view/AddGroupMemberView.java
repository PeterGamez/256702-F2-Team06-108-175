package com.mechat.view;

import java.util.ArrayList;

import com.mechat.interfaces.ViewInterface;
import com.mechat.utils.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AddGroupMemberView implements ViewInterface {

    private final ArrayList<Pane> friends = new ArrayList<>();
    private Button addGroupMemberButton;
    private Button backButton;

    public AddGroupMemberView() {
        addGroupMemberButton = TemplateView.createButton("Add Member", "add-friend-button");
        backButton = TemplateView.createImageButton("/images/back-button.png", 30, 30, "back-button");
    }

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        // header
        Label title = new Label("Add Member");
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("misc-label");

        HBox header = new HBox(backButton, title);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(20);
        header.getStyleClass().add("header");

        // content
        VBox friendList = new VBox();

        friendList.getChildren().addAll(friends);

        ScrollPane scrollPane = new ScrollPane(friendList);
        scrollPane.getStyleClass().add("scroll-pane");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox box = new VBox(scrollPane, addGroupMemberButton);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(20, 100, 70, 100));
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

        CheckBox checkBox = new CheckBox();
        checkBox.getStyleClass().add("check-box");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox chatHeader = new HBox();
        chatHeader.getChildren().addAll(profile, label, spacer, checkBox);
        chatHeader.setSpacing(20);
        chatHeader.setAlignment(Pos.CENTER_LEFT);
        chatHeader.setPadding(new Insets(0, 20, 0, 10));

        list.getChildren().add(chatHeader);
        list.getStyleClass().add("friend-box");

        friends.add(list);
    }

    public Button getAddGroupMemberButton() {
        return addGroupMemberButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
