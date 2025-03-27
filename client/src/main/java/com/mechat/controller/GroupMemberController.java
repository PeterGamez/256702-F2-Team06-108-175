package com.mechat.controller;

import com.mechat.interfaces.ControllerInterface;
import com.mechat.view.GroupMemberView;

public class GroupMemberController implements ControllerInterface {

    private GroupMemberView groupMemberView = new GroupMemberView();

    public GroupMemberController() {

    }

    @Override
    public void load() {
    }
}
