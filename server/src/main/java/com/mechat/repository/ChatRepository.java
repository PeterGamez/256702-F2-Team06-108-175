package com.mechat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c INNER JOIN ChatMember cm ON c.id = cm.chat.id WHERE cm.user.id = :userId")
    List<Chat> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT c FROM Chat c INNER JOIN ChatMember cm ON c.id = cm.chat.id WHERE cm.user.id = :senderId AND cm.user.id = :receiverId")
    Optional<Chat> findByUserId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
