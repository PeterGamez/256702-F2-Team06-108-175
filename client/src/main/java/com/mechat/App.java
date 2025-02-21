package com.mechat;

import com.mechat.interfaces.ScreenInterface;
import com.mechat.screens.MainChatScreen;

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
        ScreenInterface mainScreen = new MainChatScreen();
        Scene scene = new Scene(mainScreen.createContent(), windowWidth, windowHeight);

        String userTheme = WindowsSettings.getWindowsTheme();

        if ("dark".equals(userTheme)) {
            scene.getStylesheets().add(getClass().getResource("/styles/dark-theme.css").toExternalForm());
        } else {
            scene.getStylesheets().add(getClass().getResource("/styles/light-theme.css").toExternalForm());
        }

        scene.getStylesheets().add(getClass().getResource("/styles/theme.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
