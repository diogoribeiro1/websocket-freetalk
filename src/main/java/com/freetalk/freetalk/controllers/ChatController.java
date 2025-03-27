package com.freetalk.freetalk.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.freetalk.freetalk.kafka.ChatProducer;

@Controller
public class ChatController {

    private final ChatProducer chatProducer;

    public ChatController(ChatProducer chatProducer) {
        this.chatProducer = chatProducer;
    }

    @MessageMapping("/chat")
    public void send(String message) {
        chatProducer.sendMessage(message);
    }
}
