package com.mechat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {
    public enum Type {
        PRIVATE, GROUP
    }

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "type", length = 10)
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
