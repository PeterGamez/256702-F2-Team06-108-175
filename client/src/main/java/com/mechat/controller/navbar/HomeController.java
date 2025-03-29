package com.mechat.controller.navbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.controller.chat.ChatController;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.RestApiService;
import com.mechat.utils.NavbarController;
import com.mechat.view.navbar.HomeView;

import javafx.scene.layout.Pane;

public class HomeController extends NavbarController implements ControllerInterface {

    private HomeView homeView = new HomeView();
    private List<String> chatIds = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public HomeController() {
        homeView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        homeView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        homeView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        homeView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);
    }

    @Override
    public void load() {
        loadChats();

        String uuid = Objects.toString(MakeCache.getUser().get("id"));
        String username = Objects.toString(MakeCache.getUser().get("username"));

        homeView.getUuidProperty().set("UUID: " + uuid);
        homeView.getUserProperty().set(username);
    }

    public void loadChats() {
        homeView.getChats().clear();

        List<Map<String, Object>> chats = (List<Map<String, Object>>) MakeCache.getData("chats");
        if (chats == null) {
            return;
        }

        chats.forEach(chat -> {
            String chatId = Objects.toString(chat.get("id"));
            String chatType = Objects.toString(chat.get("type"));
            String chatName = null;
            String chatIcon = null;

            if (chatType.equals("PRIVATE")) {
                try {
                    String payload = RestApiService.getChat(chatId).block();

                    JsonNode jsonNode = objectMapper.readTree(payload);
                    Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
                    });

                    if (respond.get("status").equals("success")) {
                        Object users = respond.get("users");
                        String myId = Objects.toString(MakeCache.getUser().get("id"));
                        List<Map<String, Object>> userList = objectMapper.convertValue(users, new TypeReference<List<Map<String, Object>>>() {
                        });

                        Map<String, Object> user = userList.stream()
                                .filter(u -> !Objects.toString(u.get("id")).equals(myId))
                                .findFirst()
                                .orElse(null);
                        chatName = Objects.toString(user.get("displayName"), Objects.toString(user.get("username")));
                        chatIcon = Objects.toString(user.get("avatar"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                chatName = Objects.toString(chat.get("name"));
            }

            homeView.addChat(chatIcon, chatName);
            chatIds.add(chatId);
        });

        int i = 0;
        for (Pane chat : homeView.getChats()) {
            final int index = i;

            chat.setOnMouseClicked(e -> {
                String chatId = chatIds.get(index).toString();
                MakeCache.setChatId(chatId);
                MakeCache.getController(ChatController.class).load();
            });

            i++;
        }

        ScreenHandler.setScreen(homeView);
    }
}
