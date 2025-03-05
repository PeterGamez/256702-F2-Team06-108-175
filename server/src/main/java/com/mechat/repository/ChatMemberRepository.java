package com.mechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mechat.entity.ChatMember;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    @Query("SELECT cm FROM ChatMember cm WHERE cm.chat.id = :chatId AND cm.user.id != :userId")
    List<ChatMember> findByChatNotUser(@Param("chatId") Long chatId, @Param("userId") Long userId);
}
