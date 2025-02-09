package com.mechat;

import com.mechat.screens.MainScreen;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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

        double windowWidth = screenWidth * 0.4;
        double windowHeight = screenHeight * 0.6;

        stage.setTitle("MeChat");
        MainScreen mainScreen = new MainScreen();
        Scene scene = new Scene(mainScreen.createContent(), windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }
}
