package com.mechat.controller.chat;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.model.chat.GroupChatMemberModel;
import com.mechat.view.chat.GroupChatMemberView;

import javafx.event.ActionEvent;

public class GroupChatMemberController implements ControllerInterface {

    private GroupChatMemberView groupChatMemberView = new GroupChatMemberView();
    private GroupChatMemberModel groupChatMemberModel = new GroupChatMemberModel();

    public GroupChatMemberController() {
        groupChatMemberView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(groupChatMemberView);
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(GroupChatInfoController.class).load();
    }
}
