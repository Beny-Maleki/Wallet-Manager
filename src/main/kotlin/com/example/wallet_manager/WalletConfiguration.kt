package com.example.wallet_manager

import com.example.wallet_manager.model.entities.Account
import com.example.wallet_manager.repositories.AccountRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Configuration
class WalletConfiguration {

    @Bean
    fun databaseInitializer(accountRepository: AccountRepository) = ApplicationRunner {
        if (accountRepository.existsAccountByOwner("admin").not()) {
            var admin: Account = Account()
            admin.owner = "admin"
            admin.balance = 1
            accountRepository.save(admin)
        }
    }

    @Bean
    @Primary
    fun defaultObjectMapper(): ObjectMapper {
        return ObjectMapper()
    }

    @Bean
    fun anotherObjectMapper(): ObjectMapper {
        return ObjectMapper()
    }

}
