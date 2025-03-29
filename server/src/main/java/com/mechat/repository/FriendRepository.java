package com.mechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT f FROM Friend f WHERE f.user.id = :userId OR f.friend.id = :userId")
    List<Friend> findAllFriendsByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM Friend f WHERE (f.user.id = :userId AND f.friend.id = :friendId) OR (f.user.id = :friendId AND f.friend.id = :userId)")
    Friend findFriendsByUserId(@Param("userId") Long userId, @Param("friendId") Long friendId);

    @Query("SELECT f FROM Friend f WHERE f.user.id = :userId AND f.friend.id = :friendId")
    Friend findByUserAndFriend(@Param("userId") Long userId, @Param("friendId") Long friendId);
}
