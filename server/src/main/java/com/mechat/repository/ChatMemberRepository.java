package com.mechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechat.entity.ChatMember;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {

}
