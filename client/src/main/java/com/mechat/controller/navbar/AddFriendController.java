package com.mechat.controller.navbar;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.service.ResponseMessage;
import com.mechat.service.RestApiService;
import com.mechat.service.WebSocketClient;
import com.mechat.utils.NavbarController;
import com.mechat.view.navbar.AddFriendView;

import javafx.event.ActionEvent;

public class AddFriendController extends NavbarController implements ControllerInterface {

    private AddFriendView addFriendView = new AddFriendView();

    private ObjectMapper objectMapper = new ObjectMapper();

    private String userId = null;

    public AddFriendController() {
        addFriendView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        addFriendView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        addFriendView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        addFriendView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);

        addFriendView.getSearchField().setOnAction(this::searchEvent);
        addFriendView.getSearchField().textProperty().addListener((obs, oldVal, newVal) -> {
            addFriendView.getShowErrorProperty().set(null);
            userId = null;
        });

        addFriendView.getAddFriendButton().setOnAction(this::addFriendEvent);
    }

    @Override
    public void load() {
        addFriendView.getSearchedFriendName().set(null);
        addFriendView.getShowErrorProperty().set(null);
        userId = null;

        addFriendView.getImagePath().set(null);
        addFriendView.getShowFriendName().set(null);

        ScreenHandler.setScreen(addFriendView);
    }

    private void searchEvent(ActionEvent e) {
        String text = addFriendView.getSearchField().getText();
        try {
            String payload = RestApiService.getUserByUsername(text).block();

            JsonNode jsonNode = objectMapper.readTree(payload);
            Map<String, Object> respond = objectMapper.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
            });

            if (respond.get("status").toString().equals("error")) {
                String message = Objects.toString(respond.get("message"));
                addFriendView.getShowErrorProperty().set(message);
                return;
            }

            Map<String, Object> user = objectMapper.convertValue(respond.get("user"), new TypeReference<Map<String, Object>>() {
            });

            userId = Objects.toString(user.get("id"));
            String username = Objects.toString(user.get("displayName"), Objects.toString(user.get("username")));
            String imagePath = Objects.toString(user.get("avatar"), "/images/profile-icon.png");

            addFriendView.getShowFriendName().set(username);
            addFriendView.getImagePath().set(imagePath);
            addFriendView.getShowErrorProperty().set(null);
            addFriendView.getAddFriendButton().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addFriendEvent(ActionEvent e) {
        if (MakeCache.getUser().get("id").toString().equals(userId)) {
            addFriendView.getShowErrorProperty().set("You cannot add yourself as a friend.");
            return;
        }

        ResponseMessage respond = new ResponseMessage(16, 1);
        respond.put("user_id", userId);

        WebSocketClient.sendMessage(respond.raw());
    }
}
