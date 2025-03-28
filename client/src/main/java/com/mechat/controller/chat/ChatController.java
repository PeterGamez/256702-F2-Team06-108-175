package com.mechat.controller.chat;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.controller.navbar.HomeController;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.chat.ChatView;

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
        MakeCache.getController(FriendChatInfoController.class).load();
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(HomeController.class).load();
    }

}
