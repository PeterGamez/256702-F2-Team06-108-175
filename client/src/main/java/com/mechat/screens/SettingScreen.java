package com.mechat.screens;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SettingScreen implements ScreenInterface {

    private String user = "USER";
    private String uid = "0000000001";
    private BorderPane root;

    @Override
    public Parent createContent() {
        root = new BorderPane();

        root.setLeft(createTabBox());
        root.setCenter(createGeneralContent());

        return root;
    }

    private ImageView createImageView(String imagePath, double width, double height) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }

    private void backEvent(MouseEvent e) {
        ScreenHandler.setScreen(new MainChatScreen());
    }

    private Parent createTabBox() {
        VBox tabBox = new VBox();
        tabBox.getStyleClass().add("tab-box");

        //Header
        ImageView backButton = createImageView("images/back-button.png", 30, 30);
        backButton.setOnMouseClicked(e -> backEvent(e));

        Label settings = new Label("Settings");
        settings.getStyleClass().add("setting-label");

        HBox header = new HBox();
        header.getChildren().addAll(backButton, settings);
        header.getStyleClass().add("setting-header");
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(30);
        header.setPadding(new Insets(0, 0, 0, 20));

        //Buttons
        VBox buttonsLayout = new VBox();
        Button general = new Button("General");
        general.getStyleClass().add("tab-button");
        general.setOnAction(e -> root.setCenter(createGeneralContent()));

        Button account = new Button("Chat");
        account.getStyleClass().add("tab-button");

        Button about = new Button("About");
        about.getStyleClass().add("tab-button");
        about.setOnAction(e -> root.setCenter(createAboutContent()));

        buttonsLayout.getChildren().addAll(general, account, about);

        tabBox.getChildren().addAll(header, buttonsLayout);

        return tabBox;
    }

    private Parent createGeneralContent() {
        VBox content = new VBox();
        content.getStyleClass().add("setting-content");
        content.setPadding(new Insets(80, 60, 20, 60));
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(20);

        ImageView profile = createImageView("images/profile-icon.png", 100, 100);

        HBox showUser = new HBox();
        showUser.setAlignment(Pos.CENTER);
        Label displayName = new Label("Display Name");
        displayName.getStyleClass().add("setting-content-label");
        Label name = new Label(user);
        name.getStyleClass().add("setting-content-label");
        ImageView edit = createImageView("images/edit-icon.png", 20, 20);
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        showUser.getChildren().addAll(displayName, spacer1, name, edit);
        showUser.setSpacing(5);

        HBox showuuid = new HBox();
        showuuid.setAlignment(Pos.CENTER);
        Label displayuuid = new Label("UUID");
        displayuuid.getStyleClass().add("setting-content-label");
        Label uuid = new Label(uid);
        uuid.getStyleClass().add("setting-content-label");
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        showuuid.getChildren().addAll(displayuuid, spacer2, uuid);

        content.getChildren().addAll(profile, showUser, showuuid);

        return content;
    }

    private Parent createAboutContent() {
        VBox content = new VBox();
        content.getStyleClass().add("setting-content");
        content.setPadding(new Insets(80, 20, 20, 20));
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(10);

        Label aboutTitle = new Label("Author: Mechat Team");
        aboutTitle.getStyleClass().add("setting-label");

        Label aboutContent = new Label("หากมีข้อสงสัยหรือข้อเสนอแนะ สามารถติดต่อได้ที่ xxx-xxx-xxxx");
        aboutContent.getStyleClass().add("setting-about-label");
        content.getChildren().addAll(aboutTitle, aboutContent);

        return content;
    }
}
