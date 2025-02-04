package com.mechat.screens;

import com.mechat.interfaces.ScreenInterface;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class MainScreen implements ScreenInterface {
    public Parent createContent() {
        Label content = new Label("Hello, JavaFX!");
        content.setAlignment(Pos.CENTER);
        return content;
    }
}
