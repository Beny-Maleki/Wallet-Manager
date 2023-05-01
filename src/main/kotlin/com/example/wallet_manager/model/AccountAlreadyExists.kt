package com.example.wallet_manager.model

class AccountAlreadyExists(owner: String): Exception("account with owner $owner already exists")