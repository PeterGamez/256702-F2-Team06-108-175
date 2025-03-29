package com.mechat.controller.chat;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.controller.navbar.HomeController;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.ResponseMessage;
import com.mechat.service.RestApiService;
import com.mechat.utils.Time;
import com.mechat.view.chat.ChatView;
import com.mechat.websocket.WebSocketClient;

import javafx.event.ActionEvent;

public class ChatController implements ControllerInterface {

    private ChatView chatView = new ChatView();

    private String chatId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ChatController() {
        // chatView.getInformationButton().setOnAction(this::informationEvent);
        chatView.getBackButton().setOnAction(this::backEvent);
        chatView.getMessageField().setOnAction(this::chatEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(chatView);
        // chatView.addReceivedMessage("Hello, how are you?", Time.getCurrentTime());

        chatId = MakeCache.getChatId();
        try {
            String payload = RestApiService.getChat(chatId).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            Map<String, Object> chat = objectMapper.convertValue(respond.get("chat"), new TypeReference<Map<String, Object>>() {
            });

            List<Map<String, Object>> users = objectMapper.convertValue(respond.get("users"), new TypeReference<List<Map<String, Object>>>() {
            });

            boolean isGroup = (boolean) chat.get("type").equals("GROUP");
            if (isGroup) {
                // chatView.getInformationButton().setOnAction(this::informationGroupEvent);
            } else {
                // chatView.getInformationButton().setOnAction(this::informationPrivateEvent);
            }

            String MyId = Objects.toString(MakeCache.getUser().get("id"));

            Map<String, Object> friend = users.stream().filter(user -> !user.get("id").toString().equals(MyId)).findFirst().orElse(null);

            String friendName = Objects.toString(friend.get("displayName"), Objects.toString(friend.get("username")));
            String friendImage = Objects.toString(friend.get("avatar"), "/images/profile-icon.png");

            chatView.getFriendNameProperty().set(friendName);
            chatView.getFriendImageProperty().set(friendImage);

        } catch (Exception e) {
        }

    }

    // private void informationEvent(ActionEvent e) {
    // MakeCache.getController(FriendChatInfoController.class).load();
    // }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(HomeController.class).load();
    }

    private void chatEvent(ActionEvent e) {
        String message = chatView.getMessageField().getText();
        if (message.trim().isEmpty()) {
            return;
        }

        ResponseMessage respond = new ResponseMessage(11, 1);

        respond.put("chat_id", chatId);
        respond.put("message", message);

        WebSocketClient.sendMessage(respond.raw());

        chatView.addSentMessage(message, Time.getCurrentTime());
        chatView.getMessageField().clear();
    }

    // private void informationGroupEvent(ActionEvent e) {
    // chatView.getInformationButton().setVisible(true);
    // }

    // private void informationPrivateEvent(ActionEvent e) {
    // chatView.getInformationButton().setVisible(false);
    // }

    public void reciveMessage(String message) {
        chatView.addReceivedMessage(message, Time.getCurrentTime());
    }
}
