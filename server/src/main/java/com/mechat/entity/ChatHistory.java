package com.mechat.entity;

import com.mechat.utils.BoolToIntConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_history")
public class ChatHistory extends EntityBase {
    @Column(name = "chat_id")
    private Number chatId;

    @Column(name = "user_id")
    private Number userId;

    @Column(name = "message", length = 4096)
    private String message;

    @Column(name = "edited")
    @Convert(converter = BoolToIntConverter.class)
    private Boolean edited;

    @Column(name = "deleted")
    @Convert(converter = BoolToIntConverter.class)
    private Boolean deleted;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getEdited() {
        return edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
