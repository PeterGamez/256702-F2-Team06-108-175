package com.mechat.controller.chat;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.chat.FriendChatInfoModel;
import com.mechat.view.chat.FriendChatInfoView;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class FriendChatInfoController implements ControllerInterface {

    private FriendChatInfoView friendChatInfoView = new FriendChatInfoView();
    private FriendChatInfoModel friendChatInfoModel = new FriendChatInfoModel();

    public FriendChatInfoController() {
        friendChatInfoView.getBackButton().setOnAction(this::backEvent);
        friendChatInfoView.geteditNameButtonLayout().setOnMouseClicked(this::editNameEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(friendChatInfoView);
    }

    public void backEvent(ActionEvent e) {
        MakeCache.getController(ChatController.class).load();
    }

    public void editNameEvent(MouseEvent e) {
    }
}
