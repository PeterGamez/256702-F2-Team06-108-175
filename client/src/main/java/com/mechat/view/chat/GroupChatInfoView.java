package com.mechat.view.chat;

import com.mechat.interfaces.ViewInterface;
import com.mechat.view.TemplateView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GroupChatInfoView implements ViewInterface {

    private Button backButton;

    public GroupChatInfoView() {
        backButton = TemplateView.createImageButton("/images/back-button.png", 30, 30, "back-button");
    }

    @Override
    public Parent createContent() {
        // header
        HBox header = new HBox();

        header.getChildren().add(backButton);
        header.setAlignment(Pos.CENTER_LEFT);

        // body
        VBox body = new VBox();
        ImageView groupImage = TemplateView.createImageView("/images/profile-icon.png", 100, 100);
        Label groupName = new Label("Group's Name");
        groupName.getStyleClass().add("misc-label");

        VBox viewMemberButtonLayout = TemplateView.createButtonLayout("/images/view-member-icon.png", "View Member");
        VBox addMemberButtonLayout = TemplateView.createButtonLayout("/images/add-friend-icon.png", "Add Member");
        VBox editNameButtonLayout = TemplateView.createButtonLayout("/images/edit-nickname-icon.png", "Rename");

        HBox buttonLayout = new HBox(20, viewMemberButtonLayout, addMemberButtonLayout, editNameButtonLayout);
        buttonLayout.setAlignment(Pos.CENTER);

        body.getChildren().addAll(groupImage, groupName, buttonLayout);
        body.setSpacing(30);
        body.setAlignment(Pos.TOP_CENTER);
        body.setPadding(new Insets(20));

        VBox box = new VBox(20, header, body);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(23, 0, 0, 20));
        return box;
    }

    public Button getBackButton() {
        return backButton;
    }
}
