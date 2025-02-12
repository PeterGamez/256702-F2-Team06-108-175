package com.mechat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_attachments")
public class ChatAttachments extends BaseEntity {
    @Column(name = "chat_history_id")
    private Number chatHistoryId;

    @Column(name = "file_path", length = 100)
    private String filePath;

    public Number getChatHistoryId() {
        return chatHistoryId;
    }

    public void setChatHistoryId(Number chatHistoryId) {
        this.chatHistoryId = chatHistoryId;
    }
}
