package com.mechat;

import com.mechat.controller.home.MainController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double windowWidth = screenWidth * 0.5;
        double windowHeight = screenHeight * 0.7;

        stage.setTitle("MeChat");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/main-icon.png")));

        new ScreenHandler(stage, windowWidth, windowHeight);
        new MakeCache();

        MakeCache.getController(MainController.class).load();
    }
}
