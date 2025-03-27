package com.mechat.controller;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.ChatView;

import javafx.event.ActionEvent;

public class ChatConntroller implements ControllerInterface {

    private ChatView chatView = new ChatView();

    public ChatConntroller(ChatView view) {
        chatView.getInformationButton().setOnAction(this::informationEvent);
        chatView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {

    }

    private void informationEvent(ActionEvent e) {

    }

    private void backEvent(ActionEvent e) {

    }

}
