package com.mechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechat.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
