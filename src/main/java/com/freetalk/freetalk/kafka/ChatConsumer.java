package com.freetalk.freetalk.kafka;

import com.freetalk.freetalk.models.ChatMessage;
import com.freetalk.freetalk.repository.ChatMessageRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatConsumer {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    public ChatConsumer(SimpMessagingTemplate messagingTemplate, ChatMessageRepository chatMessageRepository) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageRepository = chatMessageRepository;
    }

    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void consume(String messageContent, @Header(KafkaHeaders.RECEIVED_KEY) String groupId) {
        ChatMessage msg = new ChatMessage();
        msg.setGroupId(groupId);
        msg.setContent(messageContent);
        msg.setSender("unknown"); // você pode ajustar isso

        chatMessageRepository.save(msg);
        messagingTemplate.convertAndSend("/topic/messages/" + groupId, msg);
    }
}
