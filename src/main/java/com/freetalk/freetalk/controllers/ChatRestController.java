package com.freetalk.freetalk.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freetalk.freetalk.models.ChatMessage;
import com.freetalk.freetalk.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatRestController {

    private final ChatService chatService;

    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/group/{groupId}")
    public List<ChatMessage> getMessagesByGroup(@PathVariable String groupId) {
        return chatService.getMessagesByGroup(groupId);
    }
}
