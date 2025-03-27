package com.freetalk.freetalk.kafka;

import com.freetalk.freetalk.models.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatProducer {

    private static final String TOPIC = "chat-messages";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(ChatMessage message) {
        kafkaTemplate.send(TOPIC, message.getGroupId(), message.getContent());
    }
}
