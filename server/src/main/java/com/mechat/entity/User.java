package com.mechat.entity;

import com.mechat.utils.Crypt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username", length = 20, unique = true)
    private String username;

    @Column(name = "password", length = 80)
    private String password;

    @Column(name = "display_name", nullable = true, length = 20)
    private String displayName;

    @Column(name = "avatar", nullable = true, length = 100)
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Crypt.encrypt(password);
    }

    public boolean verifyPassword(String password) {
        return Crypt.decrypt(password, this.password);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
