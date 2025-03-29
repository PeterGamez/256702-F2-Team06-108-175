package com.mechat.controller.navbar;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.utils.NavbarController;
import com.mechat.view.navbar.FriendListView;

public class FriendListController extends NavbarController implements ControllerInterface {

    private FriendListView friendListView = new FriendListView();

    public FriendListController() {
        friendListView.getAddFriendNavbarButton().setOnAction(this::addFriendNavbarEvent);
        friendListView.getFriendListNavbarButton().setOnAction(this::friendListNavbarEvent);
        friendListView.getHomeNavbarButton().setOnAction(this::homeNavbarEvent);
        friendListView.getSettingNavbarButton().setOnAction(this::settingNavbarEvent);
    }

    @Override
    public void load() {
        loadChats();

        ScreenHandler.setScreen(friendListView);
    }

    public void loadChats() {
        friendListView.getFriends().clear();

        List<Map<String, Object>> friends = (List<Map<String, Object>>) MakeCache.getData("friends");
        if (friends == null) {
            return;
        }

        friends.forEach(chat -> {
            String chatName = Objects.toString(chat.get("displayName"), Objects.toString(chat.get("username")));
            String chatIcon = Objects.toString(chat.get("icon"));

            friendListView.addFriends(chatIcon, chatName);
        });
    }
}
