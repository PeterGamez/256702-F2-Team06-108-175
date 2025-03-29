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

        PENDING(0), ACCEPTED(1), REJECTED(2);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Status fromValue(int value) {
            for (Status status : Status.values()) {
                if (status.getValue() == value) {
                    return status;
                }
            }

            return null;
        }

        public static Status fromValue(String value) {
            int intValue = 0;
            try {
                intValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }

            return fromValue(intValue);
        }
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
