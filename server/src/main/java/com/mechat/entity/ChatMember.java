package com.mechat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_members")
public class ChatMember extends BaseEntity {
    @Column(name = "chat_id")
    private Number chatId;

    @Column(name = "user_id")
    private Number userId;

    public Number getChatId() {
        return chatId;
    }

    public void setChatId(Number chatId) {
        this.chatId = chatId;
    }

    public Number getUserId() {
        return userId;
    }

    public void setUserId(Number userId) {
        this.userId = userId;
    }
}
