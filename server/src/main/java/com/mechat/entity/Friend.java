package com.mechat.entity;

import java.time.LocalDateTime;

import com.mechat.utils.YearConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "friends")
public class Friend extends BaseEntity {

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @Column(name = "status")
    private Status status;

    @Column(name = "accepted_at", columnDefinition = "DATETIME")
    @Convert(converter = YearConverter.class)
    private LocalDateTime acceptedAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getAcceptedAt() {
        return acceptedAt;
    }
}
