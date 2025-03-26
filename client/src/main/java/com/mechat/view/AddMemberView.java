package com.mechat.view;

import com.mechat.interfaces.ScreenInterface;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class AddMemberView implements ScreenInterface {

    @Override
    public Parent createContent() {
        BorderPane root = new BorderPane();
        return root;
    }
}