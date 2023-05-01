package com.example.wallet_manager.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class KafkaConsumer {
    @KafkaListener(topics = ["topic"])
    fun listen(message: String) {
        println("[${LocalDateTime.now()}] message received: $message")
    }
}