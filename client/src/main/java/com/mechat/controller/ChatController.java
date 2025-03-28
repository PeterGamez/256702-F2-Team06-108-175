package com.mechat.controller;

import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.ChatView;

import javafx.event.ActionEvent;

public class ChatController implements ControllerInterface {

    private ChatView chatView = new ChatView();

    public ChatController() {
        chatView.getInformationButton().setOnAction(this::informationEvent);
        chatView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(chatView);
    }

    private void informationEvent(ActionEvent e) {

    }

    private void backEvent(ActionEvent e) {

    }

}
