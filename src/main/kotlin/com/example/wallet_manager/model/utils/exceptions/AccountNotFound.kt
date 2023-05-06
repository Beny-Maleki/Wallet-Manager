package com.example.wallet_manager.model.utils.exceptions

class AccountNotFound(id: Long?): Exception("account with id=$id not found")