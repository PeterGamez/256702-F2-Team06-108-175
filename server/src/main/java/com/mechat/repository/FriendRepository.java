package com.mechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechat.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {

}