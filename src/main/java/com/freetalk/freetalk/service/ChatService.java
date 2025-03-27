package com.freetalk.freetalk.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.freetalk.freetalk.models.ChatMessage;
import com.freetalk.freetalk.repository.ChatMessageRepository;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Cacheable(value = "lastMessages")
    public List<ChatMessage> getLastMessages() {
        System.out.println("Buscando mensagens do banco...");
        return chatMessageRepository.findAll();
    }
}
