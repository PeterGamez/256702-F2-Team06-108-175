package com.mechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.Chat;
import com.mechat.entity.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

    @Query("SELECT ch FROM ChatHistory ch WHERE ch.chat.id = :chatId")
    List<Chat> findAllByChatId(@Param("chatId") Long chatId);
}
