package com.example.wallet_manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = [AccountRepository::class])
class WalletManagerApplication

fun main(args: Array<String>) {
    runApplication<WalletManagerApplication>(*args)
}
