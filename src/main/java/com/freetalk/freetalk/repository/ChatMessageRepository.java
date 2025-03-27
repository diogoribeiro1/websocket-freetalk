package com.freetalk.freetalk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freetalk.freetalk.models.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {

}