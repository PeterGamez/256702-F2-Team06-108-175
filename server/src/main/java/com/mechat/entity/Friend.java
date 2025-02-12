package com.mechat.entity;

import java.time.LocalDateTime;

import com.mechat.utils.YearConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "friends")
public class Friend extends BaseEntity {

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }

    @Column(name = "user_id")
    private Number userId;

    @Column(name = "friend_id")
    private Number friendId;

    @Column(name = "status")
    private Status status;

    @Column(name = "accepted_at", columnDefinition = "DATETIME")
    @Convert(converter = YearConverter.class)
    private LocalDateTime acceptedAt;

    public Number getUserId() {
        return userId;
    }

    public void setUserId(Number userId) {
        this.userId = userId;
    }

    public Number getFriendId() {
        return friendId;
    }

    public void setFriendId(Number friendId) {
        this.friendId = friendId;
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
