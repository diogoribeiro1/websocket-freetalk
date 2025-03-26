package com.freetalk.freetalk.controllers;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freetalk.freetalk.models.ChatMessage;

@RestController
public class ChatRestController {

    private final SimpMessagingTemplate template;

    public ChatRestController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping("/api/send")
    public void sendMessage(@RequestBody ChatMessage message) {
        template.convertAndSend("/topic/messages", message);
    }
}
