package com.mechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT f FROM Friend f WHERE f.user.id = :userId")
    List<Friend> findAllFriendsByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM Friend f WHERE f.user = :user AND f.friend = :friend")
    Friend findByUserAndFriend(UserDTO user, UserDTO friend);
}
