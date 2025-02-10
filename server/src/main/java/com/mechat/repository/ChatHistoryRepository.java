package com.mechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechat.entity.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
    
}
