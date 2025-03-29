package com.mechat.controller.chat;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.chat.GroupChatInfoView;

import javafx.event.ActionEvent;

public class GroupChatInfoController implements ControllerInterface {

    private GroupChatInfoView groupChatInfoView = new GroupChatInfoView();

    public GroupChatInfoController() {
        groupChatInfoView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(groupChatInfoView);
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(ChatController.class).load();
    }
}
