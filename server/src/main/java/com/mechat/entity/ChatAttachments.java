package com.mechat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_attachments")
public class ChatAttachments extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "chat_history_id")
    private ChatHistory chatHistory;

    @Column(name = "file_path", length = 100)
    private String filePath;

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ChatHistory chatHistory) {
        this.chatHistory = chatHistory;
    }
}
