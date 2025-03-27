package com.freetalk.freetalk.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.freetalk.freetalk.kafka.ChatProducer;
import com.freetalk.freetalk.models.ChatMessage;

@Controller
public class ChatController {

    private final ChatProducer chatProducer;

    public ChatController(ChatProducer chatProducer) {
        this.chatProducer = chatProducer;
    }

    @MessageMapping("/chat")
    public void send(ChatMessage message) {
        chatProducer.sendMessage(message);
    }
}
