package com.mechat.view.navbar;

import java.util.ArrayList;

import com.mechat.interfaces.ViewInterface;
import com.mechat.view.NavbarView;
import com.mechat.view.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FriendListView extends NavbarView implements ViewInterface {

    private ArrayList<Pane> friends = new ArrayList<>();

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();

        // header
        Label title = new Label("Friend List");
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("misc-label");

        // content
        VBox friendList = new VBox();

        friendList.getChildren().addAll(friends);

        ScrollPane scrollPane = new ScrollPane(friendList);
        scrollPane.getStyleClass().add("scroll-pane");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox box = new VBox(title, scrollPane);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(20, 100, 70, 100));
        box.setSpacing(30);

        root.setCenter(box);
        root.setBottom(super.setNavbar());

        return root;
    }

    public void addFriends(String avatar, String title) {
        VBox list = new VBox();
        list.setAlignment(Pos.CENTER);

        Label label = new Label(title);
        label.getStyleClass().add("friend-label");

        ImageView profile = TemplateView.createImageView("/images/profile-icon.png", 50, 50);

        HBox chatHeader = new HBox();
        chatHeader.getChildren().addAll(profile, label);
        chatHeader.setSpacing(20);
        chatHeader.setAlignment(Pos.CENTER_LEFT);
        chatHeader.setPadding(new Insets(0, 0, 0, 10));

        list.getChildren().add(chatHeader);
        list.getStyleClass().add("friend-box");

        friends.add(list);
    }

    public ArrayList<Pane> getFriends() {
        return friends;
    }
}
