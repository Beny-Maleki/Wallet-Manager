package com.example.wallet_manager.model.utils.exceptions

class AccountAlreadyExists(owner: String): Exception("account with owner $owner already exists")