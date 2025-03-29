package com.mechat.entity;

import com.mechat.utils.BoolToIntConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_histories")
public class ChatHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "message", length = 4096)
    private String message;

    @Column(name = "edited")
    @Convert(converter = BoolToIntConverter.class)
    private Boolean edited;

    @Column(name = "deleted")
    @Convert(converter = BoolToIntConverter.class)
    private Boolean deleted;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat= chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
