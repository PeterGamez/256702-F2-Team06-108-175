package com.mechat.controller.chat;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.controller.navbar.HomeController;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.RestApiService;
import com.mechat.utils.Time;
import com.mechat.view.chat.ChatView;

import javafx.event.ActionEvent;

public class ChatController implements ControllerInterface {

    private ChatView chatView = new ChatView();

    public ChatController() {
        // chatView.getInformationButton().setOnAction(this::informationEvent);
        chatView.getBackButton().setOnAction(this::backEvent);
        chatView.getMessageField().setOnAction(this::chat);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(chatView);
        chatView.addReceivedMessage("Hello, how are you?", Time.getCurrentTime());

        String chatId = MakeCache.getChatId();
        try {
            Object chat = RestApiService.getChat(chatId).block();
        } catch (Exception e) {
        }
        // if (isGroup) {
        // chatView.getInformationButton().setOnAction(this::informationGroupEvent);
        // } else {
        // chatView.getInformationButton().setOnAction(this::informationPrivateEvent);
        // }
    }

    // private void informationEvent(ActionEvent e) {
    // MakeCache.getController(FriendChatInfoController.class).load();
    // }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(HomeController.class).load();
    }

    private void chat(ActionEvent e) {
        String message = chatView.getMessageField().getText();
        if (!message.trim().isEmpty()) {
            chatView.addSentMessage(message, Time.getCurrentTime());
            chatView.getMessageField().clear();
        }
    }

    // private void informationGroupEvent(ActionEvent e) {
    // chatView.getInformationButton().setVisible(true);
    // }

    // private void informationPrivateEvent(ActionEvent e) {
    // chatView.getInformationButton().setVisible(false);
    // }
}
