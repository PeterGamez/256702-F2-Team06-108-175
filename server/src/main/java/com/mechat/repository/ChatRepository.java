package com.mechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c INNER JOIN ChatMember cm ON c.id = cm.chat.id WHERE cm.user.id = :userId")
    List<Chat> findAllByUserId(@Param("userId") Long userId);
}
