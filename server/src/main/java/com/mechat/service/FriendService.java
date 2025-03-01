package com.mechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechat.entity.Friend;
import com.mechat.repository.FriendRepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public List<Friend> getFriends(Long userId) {
        return friendRepository.findAllFriendsByUserId(userId);
    }
}
