package com.example.wallet_manager.controller

data class AccountCreateRequest(
        val owner: String,
        val balance: Long
)
