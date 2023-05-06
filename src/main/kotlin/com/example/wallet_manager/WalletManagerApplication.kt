package com.example.wallet_manager

import com.example.wallet_manager.repositories.AccountRepository
import com.example.wallet_manager.repositories.TransactionRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = [AccountRepository::class])
@EnableElasticsearchRepositories(basePackageClasses = [TransactionRepository::class])
class WalletManagerApplication

fun main(args: Array<String>) {
    runApplication<WalletManagerApplication>(*args)
}