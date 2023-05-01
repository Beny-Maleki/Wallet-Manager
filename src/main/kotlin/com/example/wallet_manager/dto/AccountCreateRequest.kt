package com.example.wallet_manager.dto

data class AccountCreateRequest(
        val owner: String,
        val balance: Long
)
