package com.mechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.Chat;
import com.mechat.entity.ChatAttachments;

public interface ChatAttachmentsRepository extends JpaRepository<ChatAttachments, Long> {

    @Query("SELECT ca FROM ChatAttachments ca WHERE ca.chatHistory.id = :chatHistoryId")
    List<Chat> findAllByChatId(@Param("chatHistoryId") Long chatHistoryId);
}
