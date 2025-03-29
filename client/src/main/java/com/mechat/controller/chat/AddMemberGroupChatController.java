package com.mechat.controller.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mechat.MakeCache;
import com.mechat.ScreenHandler;
import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.chat.AddMemberGroupChatView;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AddMemberGroupChatController implements ControllerInterface {

    private AddMemberGroupChatView addMemberGroupChatView = new AddMemberGroupChatView();

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    public AddMemberGroupChatController() {
        addMemberGroupChatView.getAddGroupMemberButton().setOnAction(this::addMemberEvent);
        addMemberGroupChatView.getBackButton().setOnAction(this::backEvent);
    }

    @Override
    public void load() {
        ScreenHandler.setScreen(addMemberGroupChatView);

        for (int i = 0; i < 20; i++) {
            addMemberGroupChatView.addChat("", "Friend " + (i + 1));
        }
    }

    private void addMemberEvent(ActionEvent e) {
        List<String> selectedFriends = checkBoxes.stream()
                .filter(CheckBox::isSelected)
                .map(cb -> ((Label) ((HBox) cb.getParent()).getChildren().get(1)).getText())
                .collect(Collectors.toList());

        navigateToChatView(selectedFriends);
    }

    private void backEvent(ActionEvent e) {
        MakeCache.getController(GroupChatInfoController.class).load();
    }

    private void navigateToChatView(List<String> selectedFriends) {
        MakeCache.getController(ChatController.class).load();
    }
}
