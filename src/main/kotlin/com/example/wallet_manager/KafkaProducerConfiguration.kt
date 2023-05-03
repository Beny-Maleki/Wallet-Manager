package com.example.wallet_manager

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@Configuration
class KafkaProducerConfiguration(
        @Value(value = "\${spring.kafka.producer.bootstrap-servers}")
        val bootstrapAddress: String,
) {
    @Bean
    fun producerFactory(

    ): ProducerFactory<String, String> {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        return DefaultKafkaProducerFactory(configs)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }
}