package com.example.wallet_manager

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
class KafkaConsumerConfiguration (
        @Value(value = "\${spring.kafka.bootstrap-servers}")
        val bootstrapAddress: String
) {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configs[ConsumerConfig.GROUP_ID_CONFIG] = "groupId"
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class
        return DefaultKafkaConsumerFactory(configs)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, String> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}