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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SettingView extends NavbarView implements ViewInterface {

    private StringProperty userProperty;
    private StringProperty uuidProperty;

    private Button generalButton;
    private Button aboutButton;
    private Button logOutButton;

    // private ImageView editButton;

    private BorderPane root;

    public SettingView() {
        userProperty = new SimpleStringProperty();
        uuidProperty = new SimpleStringProperty();

        generalButton = new Button();
        aboutButton = new Button();
        logOutButton = new Button();

        // editButton = TemplateView.createImageView("/images/edit-icon.png", 20, 20);
    }

    @Override
    public Parent createContent() {
        root = new BorderPane();

        root.setLeft(createTabBox());
        root.setCenter(generalContent());
        root.setBottom(super.setNavbar());

        return root;
    }

    public Parent createAboutContent() {
        root = new BorderPane();

        root.setLeft(createTabBox());
        root.setCenter(aboutContent());
        root.setBottom(super.setNavbar());

        return root;
    }

    private Parent createTabBox() {
        VBox tabBox = new VBox();
        tabBox.getStyleClass().add("tab-box");

        // Header
        Label settings = new Label("Settings");
        settings.getStyleClass().add("setting-label");

        HBox header = new HBox();
        header.getChildren().add(settings);
        header.getStyleClass().add("setting-header");
        header.setAlignment(Pos.CENTER);

        // Buttons
        VBox buttonsLayout = new VBox();
        generalButton.setText("General");
        generalButton.getStyleClass().add("tab-button");

        logOutButton.setText("Log Out");
        logOutButton.getStyleClass().add("tab-button");

        aboutButton.setText("About");
        aboutButton.getStyleClass().add("tab-button");

        buttonsLayout.getChildren().addAll(generalButton, aboutButton, logOutButton);

        tabBox.getChildren().addAll(header, buttonsLayout);
        tabBox.setAlignment(Pos.TOP_CENTER);

        return tabBox;
    }

    private Parent generalContent() {
        VBox content = new VBox();
        content.getStyleClass().add("setting-content");
        content.setPadding(new Insets(80, 60, 20, 60));
        content.setAlignment(Pos.TOP_CENTER);
        content.setSpacing(20);

        ImageView profile = TemplateView.createImageView("/images/profile-icon.png", 100, 100);

        HBox showUser = new HBox();
        showUser.setAlignment(Pos.CENTER);
        Label displayName = new Label("Display Name");
        displayName.getStyleClass().add("setting-content-label");
        Label name = new Label();
        name.getStyleClass().add("setting-content-label");
        name.textProperty().bind(userProperty);

        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        showUser.getChildren().addAll(displayName, spacer1, name);
        showUser.setSpacing(5);

        HBox showuuid = new HBox();
        showuuid.setAlignment(Pos.CENTER);
        Label displayuuid = new Label("UUID");
        displayuuid.getStyleClass().add("setting-content-label");
        Label uuid = new Label();
        uuid.getStyleClass().add("setting-content-label");
        uuid.textProperty().bind(uuidProperty);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        showuuid.getChildren().addAll(displayuuid, spacer2, uuid);

        content.getChildren().addAll(profile, showUser, showuuid);

        return content;
    }

    private Parent aboutContent() {
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

    public StringProperty getUserProperty() {
        return userProperty;
    }

    public StringProperty getUuidProperty() {
        return uuidProperty;
    }

    public Button getGeneralButton() {
        return generalButton;
    }

    public Button getAboutButton() {
        return aboutButton;
    }

    public Button getLogOutButton() {
        return logOutButton;
    }
}
