package com.example.wallet_manager.service

import com.example.wallet_manager.dto.AccountUpdateRequest
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.websocket.SendResult
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class KafkaProducer (
        private val kafkaTemplate: KafkaTemplate<String, String>,
        private val objectMapper: ObjectMapper
) {
    fun <T> sendMessage(topicName: String, message: T) {
        kafkaTemplate.send(topicName, objectMapper.writeValueAsString(message))
    }
}