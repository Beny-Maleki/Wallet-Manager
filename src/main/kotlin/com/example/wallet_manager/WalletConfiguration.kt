package com.example.wallet_manager

import com.example.wallet_manager.model.entities.Account
import com.example.wallet_manager.repositories.AccountRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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
}