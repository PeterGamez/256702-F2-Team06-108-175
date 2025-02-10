package com.mechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechat.entity.ChatAttachments;

public interface ChatAttachmentsRepository extends JpaRepository<ChatAttachments, Long> {

}
