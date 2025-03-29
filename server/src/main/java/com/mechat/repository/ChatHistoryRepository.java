package com.mechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

    @Query("SELECT ch FROM ChatHistory ch WHERE ch.chat.id = :chatId")
    List<ChatHistory> findAllByChatId(@Param("chatId") Long chatId);

    @Query("UPDATE ChatHistory ch SET ch.message = :message, ch.edited = 1 WHERE ch.chat.id = :chatId AND ch.id = :messageId")
    void updateChatHistory(Long chatId, Long messageId, String message);

    @Query("UPDATE ChatHistory ch SET ch.deleted = 1 WHERE ch.chat.id = :chatId AND ch.id = :messageId")
    void deleteChatHistory(Long chatId, Long messageId);
}
