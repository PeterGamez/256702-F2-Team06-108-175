package com.mechat.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechat.entity.Chat;
import com.mechat.repository.ChatRepository;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getChats(Long userId) {
        return chatRepository.findAllByUserId(userId);
    }
}
