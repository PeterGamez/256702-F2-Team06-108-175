package com.mechat.restAPI.V1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechat.dto.UserDTO;
import com.mechat.entity.Chat;
import com.mechat.entity.ChatHistory;
import com.mechat.entity.ChatMember;
import com.mechat.service.ChatService;

@RestController
@RequestMapping("/v1/chat")
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getChat(@PathVariable("id") Long id) {
        log.info("Get chat endpoint called with ID: " + id);
        Chat chat = chatService.getChatById(id);
        List<ChatMember> users = chatService.getChatUsers(chat);
        List<UserDTO> userDTOs = new ArrayList<>();

        users.forEach(d -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(d.getUser().getId());
            userDTO.setUsername(d.getUser().getUsername());
            userDTO.setDisplayName(d.getUser().getDisplayName());
            userDTO.setAvatar(d.getUser().getAvatar());
            userDTOs.add(userDTO);
        });

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("chat", chat);
        response.put("users", userDTOs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<?> getChatHistory(@PathVariable("id") Long id) {
        log.info("Get chat history endpoint called with ID: " + id);

        Chat chat = chatService.getChatById(id);
        List<ChatHistory> chatHistories = chatService.getChatHistory(chat);

        List<Map<String, Object>> histories = new ArrayList<>();

        chatHistories.forEach(history -> {
            Map<String, Object> historyMap = new LinkedHashMap<>();
            historyMap.put("id", history.getId());
            historyMap.put("message", history.getMessage());
            historyMap.put("sender_id", history.getUser().getId());
            historyMap.put("edited", history.getEdited());
            historyMap.put("deleted", history.getDeleted());
            historyMap.put("createdAt", history.getCreatedAt());
            historyMap.put("updatedAt", history.getUpdatedAt());
            histories.add(historyMap);
        });

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("histories", histories);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
