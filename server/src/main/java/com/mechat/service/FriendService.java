package com.mechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Friend;
import com.mechat.repository.FriendRepository;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserService userService;

    public List<Friend> getFriends(Long userId) {
        return friendRepository.findAllFriendsByUserId(userId);
    }

    public Friend getFriends(Long userId, Long friendId) {
        return friendRepository.findFriendsByUserId(userId, friendId);
    }

    public void addFriend(UserDTO user, UserDTO friend) {
        Friend friendD = new Friend();
        friendD.setUser(userService.convertToEntity(user));
        friendD.setFriend(userService.convertToEntity(friend));
        friendD.setStatus(Friend.Status.ACCEPTED);

        friendRepository.save(friendD);
    }

    public void updateFriend(UserDTO user, UserDTO friend, Friend.Status status) {
        Friend friendD = friendRepository.findByUserAndFriend(user.getId(), friend.getId());

        friendD.setStatus(status);

        friendRepository.save(friendD);
    }
}
