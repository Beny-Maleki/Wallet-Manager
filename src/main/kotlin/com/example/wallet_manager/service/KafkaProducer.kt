package com.example.wallet_manager.service

import jakarta.websocket.SendResult
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class KafkaProducer (
        val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun sendMessage(topicName: String, message: String) {
        kafkaTemplate.send(topicName, message)
    }
}