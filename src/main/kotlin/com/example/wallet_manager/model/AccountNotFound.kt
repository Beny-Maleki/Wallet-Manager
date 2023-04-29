package com.example.wallet_manager.model

class AccountNotFound(id: Long): Exception("account with id=$id not found")