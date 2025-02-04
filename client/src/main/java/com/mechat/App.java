package com.mechat;

import com.mechat.screens.MainScreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX App");
        MainScreen mainView = new MainScreen();
        Scene scene = new Scene(mainView.createContent(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
