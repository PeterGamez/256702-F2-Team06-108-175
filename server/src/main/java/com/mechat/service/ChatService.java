package com.mechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Chat;
import com.mechat.entity.ChatHistory;
import com.mechat.entity.ChatMember;
import com.mechat.repository.ChatHistoryRepository;
import com.mechat.repository.ChatMemberRepository;
import com.mechat.repository.ChatRepository;

@Service
public class ChatService {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Autowired
    private ChatMemberRepository chatMemberRepository;

    public List<Chat> getChats(Long userId) {
        return chatRepository.findAllByUserId(userId);
    }

    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }

    public Chat getChatByUserId(Long senderId, Long receiverId) {
        return chatRepository.findByUserId(senderId, receiverId).orElse(null);
    }

    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public void saveChatHistory(Chat chat, UserDTO user, String message) {
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setChat(chat);
        chatHistory.setUser(userService.convertToEntity(user));
        chatHistory.setMessage(message);

        chatHistoryRepository.save(chatHistory);
    }

    public UserDTO getChatUser(Chat chat, UserDTO user) {
        List<ChatMember> chatMember = chatMemberRepository.findByChatNotUser(chat.getId(), user.getId());
        UserDTO userDTO = userService.convertToDTO(chatMember.get(0).getUser());
        return userDTO;
    }

    public List<UserDTO> getChatUsers(Chat chat, UserDTO user) {
        List<ChatMember> chatMember = chatMemberRepository.findByChatNotUser(chat.getId(), user.getId());
        List<UserDTO> users = chatMember.stream().map(cm -> userService.convertToDTO(cm.getUser())).toList();
        return users;
    }

    public void saveChatUser(Chat chat, UserDTO user) {
        ChatMember chatMember = new ChatMember();
        chatMember.setChat(chat);
        chatMember.setUser(userService.convertToEntity(user));

        chatMemberRepository.save(chatMember);
    }
}
